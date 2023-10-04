package com.won983212.boardgame.domain.game.omok;

import com.won983212.boardgame.domain.game.packet.Packet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HandshakePacket implements Packet {
    private final Long roomId;
    private final String auth;
}
