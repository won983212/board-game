package com.won983212.boardgame.domain.player.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    private final String username;
    private final String password;
}