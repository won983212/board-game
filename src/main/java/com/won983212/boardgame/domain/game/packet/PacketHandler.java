package com.won983212.boardgame.domain.game.packet;

import org.springframework.web.socket.WebSocketSession;

@FunctionalInterface
public interface PacketHandler<T extends Packet> {
    void handle(WebSocketSession session, T packet);
}
