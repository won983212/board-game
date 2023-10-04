package com.won983212.boardgame.domain.game.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RawMessage {
    private final String type;
    private final String data;
}
