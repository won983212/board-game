package com.won983212.boardgame.domain.room.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Room {
	private final Long roomId;
	private final String name;
	private final Long masterPlayerId;

	public static Room of(String name, Long masterPlayerId) {
		return new Room(null, name, masterPlayerId);
	}

	public Room withId(Long id) {
		return new Room(id, name, masterPlayerId);
	}
}
