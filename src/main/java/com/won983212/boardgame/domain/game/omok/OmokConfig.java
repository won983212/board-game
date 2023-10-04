package com.won983212.boardgame.domain.game.omok;

import com.won983212.boardgame.domain.game.config.GameConfigurer;
import com.won983212.boardgame.domain.game.config.PacketRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OmokConfig implements GameConfigurer {

    private final OmokMessageHandler messageHandler;

    @Override
    public void registerPackets(PacketRegistry registry) {
        registry.prefix("omok-")
                .packet("joinRoom", HandshakePacket.class, messageHandler::handleHandshake);
    }
}
