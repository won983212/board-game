package com.won983212.boardgame.domain.controller;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.domain.room.dto.CreateRoomRequest;
import com.won983212.boardgame.domain.room.dto.RoomResponse;
import com.won983212.boardgame.domain.room.service.RoomService;
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
    public String index(Model model) {
        List<RoomResponse> roomViewModels = roomService.getRooms().stream()
                .map((room) -> RoomResponse.from(room,
                        playerService.findById(room.getMasterPlayerId())
                                .map(Player::getName)
                                .orElse("Unknown")))
                .toList();

        // TODO test data
        model.addAttribute("playerName", "이현");
        model.addAttribute("rooms", roomViewModels);
        model.addAttribute("createRoomRequest", new CreateRoomRequest("", GameType.OMOK));
        return "room/list";
    }

    @PostMapping
    @UserAuth
    public String createRoom(@ModelAttribute CreateRoomRequest createRoomRequest) {
        return "redirect:/room";
    }
}
