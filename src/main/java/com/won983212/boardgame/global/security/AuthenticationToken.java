package com.won983212.boardgame.global.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationToken {

    public static final String COOKIE_NAME = "auth";
    private final String auth;

    public static AuthenticationToken of(String auth) {
        return new AuthenticationToken(auth);
    }
}
