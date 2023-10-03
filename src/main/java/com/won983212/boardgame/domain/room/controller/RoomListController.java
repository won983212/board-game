package com.won983212.boardgame.domain.room.controller;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.domain.room.dto.CreateRoomRequest;
import com.won983212.boardgame.domain.room.dto.RoomResponse;
import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.service.RoomService;
import com.won983212.boardgame.global.security.AppAuthentication;
import com.won983212.boardgame.global.security.role.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomListController {

    private final RoomService roomService;
    private final PlayerService playerService;

    @GetMapping
    @UserAuth
    public String index(AppAuthentication auth, Model model) {
        List<RoomResponse> roomViewModels = roomService.getRooms().stream()
                .map((room) -> RoomResponse.from(room, getUsername(room.getMasterPlayerId())))
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

    @PostMapping
    @UserAuth
    public String createRoom(AppAuthentication auth, @ModelAttribute CreateRoomRequest dto) {
        Room room = roomService.createRoom(dto.getRoomName(), dto.getGameType(), auth.getUserId());
        return "redirect:/game/" + room.getRoomId();
    }
}
