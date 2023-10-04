package com.won983212.boardgame.domain.game.config;

import com.won983212.boardgame.domain.game.packet.Packet;
import com.won983212.boardgame.domain.game.packet.PacketHandler;
import com.won983212.boardgame.domain.game.packet.PacketManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PacketRegistry {
    private final PacketManager packetManager;
    private String prefix = "";

    public PacketRegistry prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public <T extends Packet> PacketRegistry packet(String key, Class<T> packetType, PacketHandler<T> handler) {
        key = prefix + key;
        packetManager.registerPacket(key, packetType, handler);
        return this;
    }
}
