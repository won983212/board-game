package com.won983212.boardgame.domain.game.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameType {
	OMOK("오목", 2);

	private final String label;
	private final int maxPlayers;
}
