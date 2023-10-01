package com.won983212.boardgame.domain.player.controller;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.player.dto.LoginRequest;
import com.won983212.boardgame.domain.room.dto.CreateRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest(""));
        return "login";
    }

    @PostMapping
    public void doLogin(@ModelAttribute LoginRequest loginRequest) {

    }
}
