package com.won983212.boardgame.domain.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping("/{id}")
    public String game(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roomId", id);
        return "game/omok";
    }
}
