package com.won983212.boardgame.domain.player.service;

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
public class PlayerService {

    private final PlayerRepository repository;

    public Optional<Player> findById(Long id) {
        return repository.findById(id);
    }
}
