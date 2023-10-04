package com.won983212.boardgame.domain.game.controller;

import com.won983212.boardgame.domain.game.exception.NotFoundRoomException;
import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final RoomService roomService;

    // TODO 정원이 꽉 차면 못들어오게 해야한다.
    // TODO 동시에 정원이 차면 게임을 시작하도록 한다.
    @GetMapping("/{id}")
    public String game(@PathVariable("id") Long id, Model model) {
        Room room = roomService.findById(id).orElseThrow(NotFoundRoomException::new);
        model.addAttribute("roomId", id);
        return "game/" + room.getGameType().name().toLowerCase();
    }
}
