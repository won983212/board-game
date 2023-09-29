package com.won983212.boardgame.domain.player.service;

import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
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
