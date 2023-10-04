package com.won983212.boardgame.domain.game.packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class PacketManager {
    private final Map<String, PacketHandlerInfo<? extends Packet>> handlers = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;

    public <T extends Packet> void registerPacket(String url, Class<T> packetType, PacketHandler<T> handler) {
        PacketHandlerInfo<T> info = new PacketHandlerInfo<>(packetType, handler);
        handlers.put(url, info);
    }

    public <T extends Packet> void handlePacket(String url, String payload) throws JsonProcessingException {
        PacketHandlerInfo<T> info = (PacketHandlerInfo<T>) handlers.get(url);
        if (info != null) {
            Packet packet = mapper.readValue(payload, info.packetType);
            info.handler.handle((T) packet);
        }
    }

    private record PacketHandlerInfo<T extends Packet>(Class<T> packetType, PacketHandler<T> handler) {
    }
}
