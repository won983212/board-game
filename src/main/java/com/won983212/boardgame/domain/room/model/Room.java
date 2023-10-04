package com.won983212.boardgame.domain.room.model;

import com.won983212.boardgame.domain.game.enums.GameType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Room {
    private final Long roomId;
    private final String name;
    private final GameType gameType;
    private final Long masterPlayerId;

    public static Room of(String name, GameType type, Long masterPlayerId) {
        return new Room(null, name, type, masterPlayerId);
    }

    public Room withId(Long id) {
        return new Room(id, name, gameType, masterPlayerId);
    }
}
