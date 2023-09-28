package com.won983212.boardgame.domain.game.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameInfo {
	OMOK("오목", 2);

	private final String label;
	private final int maxPlayers;
}
