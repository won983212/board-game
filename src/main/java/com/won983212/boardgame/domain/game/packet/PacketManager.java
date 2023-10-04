package com.won983212.boardgame.domain.game.packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class PacketManager {
    private final Map<String, PacketHandlerInfo<? extends Packet>> handlers = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;

    public <T extends Packet> void registerPacket(String key, Class<T> packetType, PacketHandler<T> handler) {
        PacketHandlerInfo<T> info = new PacketHandlerInfo<>(packetType, handler);
        handlers.put(key, info);
    }

    public <T extends Packet> void handlePacket(WebSocketSession session, String key, String payload) throws Exception {
        PacketHandlerInfo<T> info = (PacketHandlerInfo<T>) handlers.get(key);
        if (info != null) {
            Packet packet = mapper.readValue(payload, info.packetType);
            info.handler.handle(session, (T) packet);
        }
    }

    private record PacketHandlerInfo<T extends Packet>(Class<T> packetType, PacketHandler<T> handler) {
    }
}
