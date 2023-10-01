package com.won983212.boardgame.global.security;

import com.won983212.boardgame.global.security.role.UserRole;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {

    @Value("${auth.jwt.expires}")
    private final Duration expires;

    @Value("${auth.jwt.secrets}")
    private final String secrets;

    public AuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(AuthenticationToken.COOKIE_NAME)) {
                    return AuthenticationToken.of(cookie.getValue());
                }
            }
        }
        return null;
    }

    public void saveAuthenticationCookie(HttpServletResponse response, AuthenticationToken token) {
        Cookie cookie = new Cookie(AuthenticationToken.COOKIE_NAME, token.getAuth());
        cookie.setMaxAge((int) expires.toSeconds());
        response.addCookie(cookie);
    }

    public AppAuthentication getAuthenticationDetail(AuthenticationToken token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secrets.getBytes())
                .parseClaimsJws(token.getAuth());

        Claims body = claims.getBody();
        Long userId = Long.parseLong((String) body.get("userId"));
        UserRole userRole = UserRole.from((String) body.get("userRole"));

        return new AppAuthentication(userId, userRole);
    }

    private AuthenticationToken createToken(String userId, UserRole role) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = now.plus(expires);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", userId);
        payloads.put("userRole", role.getName());

        String token = Jwts.builder()
                .setSubject("UserInfo")
                .setClaims(payloads)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(validity.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secrets.getBytes())
                .compact();

        return AuthenticationToken.of(token);
    }
}
