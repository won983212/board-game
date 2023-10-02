package com.won983212.boardgame.domain.auth.controller;

import com.won983212.boardgame.domain.auth.dto.LoginRequest;
import com.won983212.boardgame.domain.auth.service.LoginService;
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

    private final LoginService loginService;
    private final AuthenticationProvider authenticationProvider;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping
    public String doLogin(HttpServletResponse response, @ModelAttribute LoginRequest loginRequest) {
        AuthenticationToken token = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token != null) {
            authenticationProvider.saveAuthenticationCookie(response, token);
        }
        return "redirect:/room";
    }
}
