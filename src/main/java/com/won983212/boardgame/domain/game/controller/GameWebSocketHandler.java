package com.won983212.boardgame.domain.game.controller;

import com.won983212.boardgame.domain.game.packet.PacketManager;
import com.won983212.boardgame.domain.game.session.GameSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GameWebSocketHandler extends TextWebSocketHandler {

    private final GameSessionService gameSessionService;
    private final PacketManager packetManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        URI uri = session.getUri();
        if (uri != null) {
            packetManager.handlePacket(uri.getPath(), message.getPayload());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        gameSessionService.leavePlayer(session);
    }
}
