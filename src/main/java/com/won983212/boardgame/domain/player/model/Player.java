package com.won983212.boardgame.domain.player.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {
	private final Long playerId;
	private final String name;

	public static Player of(String name) {
		return new Player(null, name);
	}

	public Player withId(Long id) {
		return new Player(id, name);
	}
}
