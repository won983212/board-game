package com.won983212.boardgame.domain.game.config;

import com.won983212.boardgame.domain.game.packet.Packet;
import com.won983212.boardgame.domain.game.packet.PacketHandler;
import com.won983212.boardgame.domain.game.packet.PacketManager;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@RequiredArgsConstructor
public class PacketRegistry {
    private final PacketManager packetManager;
    private Path prefix = Path.of("/");

    public PacketRegistry pathPrefix(String prefix) {
        this.prefix = Path.of(prefix);
        return this;
    }

    public <T extends Packet> PacketRegistry packet(String url, Class<T> packetType, PacketHandler<T> handler) {
        url = prefix.resolve(url).toString();
        packetManager.registerPacket(url, packetType, handler);
        return this;
    }
}
