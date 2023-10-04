package com.won983212.boardgame.domain.game.config;

import com.won983212.boardgame.domain.game.omok.HandshakePacket;
import com.won983212.boardgame.domain.game.session.GameSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultGameMessageHandler {

    private final GameSessionService gameSessionService;

    public void handleHandshake(WebSocketSession session, HandshakePacket packet) {
        gameSessionService.joinPlayer(packet.getRoomId(), session);
    }
}
