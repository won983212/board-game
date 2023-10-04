package com.won983212.boardgame.domain.room.dto;

import com.won983212.boardgame.domain.room.model.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomResponse {

    private final Long roomId;
    private final String name;
    private final String gameName;
    private final String masterPlayerName;
    private final int players;
    private final int maxPlayers;

    public static RoomResponse from(Room room, String masterPlayerName, int players) {
        return new RoomResponse(room.getRoomId(),
                room.getName(),
                room.getGameType().getLabel(),
                masterPlayerName,
                players,
                room.getGameType().getMaxPlayers());
    }
}
