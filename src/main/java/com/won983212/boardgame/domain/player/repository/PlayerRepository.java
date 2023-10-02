package com.won983212.boardgame.domain.player.repository;

import com.won983212.boardgame.domain.player.model.Player;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
