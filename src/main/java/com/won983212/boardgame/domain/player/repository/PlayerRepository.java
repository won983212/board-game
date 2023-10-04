package com.won983212.boardgame.domain.player.repository;

import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.repository.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {

    private final PlayerMapper mapper;

    public Player save(Player player) {
        mapper.save(player);
        return player;
    }

    public void rename(long id, String newName) {
        mapper.rename(id, newName);
    }

    public Optional<Player> findById(long id) {
        return mapper.findById(id);
    }

    public Optional<Player> findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}
