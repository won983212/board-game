package com.won983212.boardgame.global.security.role;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.won983212.boardgame.global.security.role.AuthNames.*;

@Getter
public enum UserRole {
    USER(ROLE_USER),
    GUEST(ROLE_GUEST),
    ADMIN(combine(ROLE_ADMIN, ROLE_USER));

    private final String name;
    private static final Map<String, UserRole> LABEL_CACHE =
            Stream.of(values()).collect(Collectors.toMap(UserRole::getName, e -> e));

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole from(String name) {
        return LABEL_CACHE.get(name);
    }
}
