package com.won983212.boardgame.domain.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won983212.boardgame.domain.game.packet.PacketManager;
import com.won983212.boardgame.domain.game.packet.RawMessage;
import com.won983212.boardgame.domain.game.session.GameSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class GameWebSocketHandler extends TextWebSocketHandler {

    private final GameSessionService gameSessionService;
    private final PacketManager packetManager;
    private final ObjectMapper mapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        RawMessage rawMessage = mapper.readValue(message.getPayload(), RawMessage.class);
        packetManager.handlePacket(session, rawMessage.getType(), rawMessage.getData());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        gameSessionService.leavePlayer(session);
    }
}
