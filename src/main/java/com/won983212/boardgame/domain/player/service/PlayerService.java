package com.won983212.boardgame.domain.player.service;

import com.won983212.boardgame.domain.player.exception.WrongPasswordException;
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
public class PlayerService {

    private final PlayerRepository repository;
    private final AuthenticationProvider authenticationProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<Player> findById(Long id) {
        return repository.findById(id);
    }

    public AuthenticationToken login(String username, String password) {
        Optional<Player> p = repository.findByUsername(username);

        Player player;
        String encodedPwd = passwordEncoder.encode(password);

        if (p.isPresent()) {
            player = p.get();
            if (!player.getPassword().equals(encodedPwd)) {
                throw new WrongPasswordException();
            }
        } else {
            player = Player.of(username, encodedPwd);
            player = repository.save(player);
        }

        return authenticationProvider.createToken(player.getId(), player.getUserRole());
    }
}
