package com.won983212.boardgame.domain.game.packet;

@FunctionalInterface
public interface PacketHandler<T extends Packet> {
    void handle(T packet);
}
