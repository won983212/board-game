package com.won983212.boardgame.global.security.role;

public class AuthNames {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_GUEST = "ROLE_GUEST";

    public static String combine(String... names) {
        return String.join(",", names);
    }
}
