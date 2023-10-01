package com.won983212.boardgame.domain.player.controller;

import com.won983212.boardgame.domain.player.dto.LoginRequest;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.global.security.AuthenticationProvider;
import com.won983212.boardgame.global.security.AuthenticationToken;
import jakarta.servlet.http.HttpServletResponse;
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

    private final PlayerService playerService;
    private final AuthenticationProvider authenticationProvider;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping
    public void doLogin(HttpServletResponse response, @ModelAttribute LoginRequest loginRequest) {
        AuthenticationToken token = playerService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token != null) {
            authenticationProvider.saveAuthenticationCookie(response, token);
        }
    }
}
