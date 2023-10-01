package com.won983212.boardgame.domain.player.model;

import com.won983212.boardgame.global.security.role.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {
    private final Long id;
    private final String name;
    private final String password;
    private final UserRole userRole;

    public static Player of(String name, String password) {
        return new Player(0L, name, password, UserRole.USER);
    }
}
