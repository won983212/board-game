package com.won983212.boardgame.domain.player.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.won983212.boardgame.domain.player.model.Player;

@Repository
public class PlayerRepository {

    public Optional<Player> findById(Long id) {
        return Optional.empty();
    }

    public Optional<Player> findByUsername(String username) {
        return Optional.empty();
    }

    public Player save(Player player) {
        return player;
    }
}
