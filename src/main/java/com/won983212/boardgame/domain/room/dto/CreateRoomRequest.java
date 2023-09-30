package com.won983212.boardgame.domain.room.dto;

import com.won983212.boardgame.domain.game.enums.GameType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CreateRoomRequest {
    private final String roomName;
    private final GameType gameType;
}
