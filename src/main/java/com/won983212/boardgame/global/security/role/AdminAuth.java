package com.won983212.boardgame.global.security.role;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured(AuthNames.ROLE_ADMIN)
public @interface AdminAuth {
}