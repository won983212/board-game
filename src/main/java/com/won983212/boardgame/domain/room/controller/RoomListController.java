package com.won983212.boardgame.domain.room.controller;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.game.session.GameSession;
import com.won983212.boardgame.domain.game.session.GameSessionService;
import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.domain.room.dto.CreateRoomRequest;
import com.won983212.boardgame.domain.room.dto.RoomResponse;
import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.service.RoomService;
import com.won983212.boardgame.global.security.AppAuthentication;
import com.won983212.boardgame.global.security.role.UserAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomListController {

    private final RoomService roomService;
    private final GameSessionService gameSessionService;
    private final PlayerService playerService;

    @GetMapping
    @UserAuth
    public String index(AppAuthentication auth, Model model) {
        List<RoomResponse> roomViewModels = roomService.getRooms().stream()
                .map((room) -> RoomResponse.from(room,
                        getUsername(room.getMasterPlayerId()),
                        getPlayerCount(room.getRoomId())))
                .toList();

        model.addAttribute("playerName", getUsername(auth.getUserId()));
        model.addAttribute("rooms", roomViewModels);
        model.addAttribute("gameTypes", GameType.values());
        model.addAttribute("createRoomRequest", new CreateRoomRequest("", GameType.OMOK));
        return "roomList";
    }

    private String getUsername(Long playerId) {
        return playerService.findById(playerId)
                .map(Player::getName)
                .orElse("Unknown");
    }

    private int getPlayerCount(Long roomId) {
        GameSession session = gameSessionService.getGameSession(roomId);
        if (session == null) {
            log.warn("등록되어있지 않은 roomId를 조회합니다: " + roomId);
            return 0;
        }
        return session.countPlayer();
    }

    @PostMapping
    @UserAuth
    public String createRoom(AppAuthentication auth, @ModelAttribute CreateRoomRequest dto) {
        Room room = roomService.createRoom(dto.getRoomName(), dto.getGameType(), auth.getUserId());
        return "redirect:/game/" + room.getRoomId();
    }
}
