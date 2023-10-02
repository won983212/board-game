package com.won983212.boardgame.domain.room.dto;

import com.won983212.boardgame.domain.game.enums.GameType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateRoomRequest {
    private final String roomName;
    private final GameType gameType;
}
