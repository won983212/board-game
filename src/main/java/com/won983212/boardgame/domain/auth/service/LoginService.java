package com.won983212.boardgame.domain.auth.service;

import com.won983212.boardgame.domain.auth.exception.WrongPasswordException;
import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.repository.PlayerRepository;
import com.won983212.boardgame.global.security.AuthenticationProvider;
import com.won983212.boardgame.global.security.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PlayerRepository repository;
    private final AuthenticationProvider authenticationProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationToken login(String username, String password) {
        Optional<Player> p = repository.findByUsername(username);
        Player player;

        if (p.isPresent()) {
            player = p.get();
            if (!passwordEncoder.matches(password, player.getPassword())) {
                throw new WrongPasswordException();
            }
        } else {
            String encodedPwd = passwordEncoder.encode(password);
            player = Player.of(username, encodedPwd);
            player = repository.save(player);
        }

        return authenticationProvider.createToken(player.getId(), player.getUserRole());
    }
}
