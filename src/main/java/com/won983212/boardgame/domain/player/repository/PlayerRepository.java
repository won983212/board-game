package com.won983212.boardgame.domain.player.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.won983212.boardgame.domain.player.model.Player;

@Repository
public class PlayerRepository {
	// TODO redis로 교체: 동시성 이슈, 데이터 안정성, 확장 가능성
	private final Map<Long, Player> players = new HashMap<>();
	private static long nextId = 1;

	public Optional<Player> findById(Long id) {
		return Optional.ofNullable(players.get(id));
	}

	public void save(Player player) {
		long id = nextId++;
		players.put(id, player.withId(id));
	}
}
