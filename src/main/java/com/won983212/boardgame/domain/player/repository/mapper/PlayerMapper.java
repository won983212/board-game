package com.won983212.boardgame.domain.player.repository.mapper;

import com.won983212.boardgame.domain.player.model.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PlayerMapper {
    void save(Player player);

    void rename(long playerId, String newName);

    Optional<Player> findById(long playerId);

    Optional<Player> findByUsername(String username);
}
