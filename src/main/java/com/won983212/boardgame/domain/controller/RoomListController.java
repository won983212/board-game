package com.won983212.boardgame.domain.controller;

import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.domain.room.dto.RoomResponse;
import com.won983212.boardgame.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "room/list";
    }

    @PostMapping
    public void createRoom() {

    }
}
