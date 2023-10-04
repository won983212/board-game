package com.won983212.boardgame.domain.game.session;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class GameSession {
    private final Map<String, WebSocketSession> playerSessions = new ConcurrentHashMap<>();
    private final GameContext gameContext;

    public void addPlayerSession(WebSocketSession session) {
        playerSessions.put(session.getId(), session);
    }

    public void removePlayerSession(WebSocketSession session) {
        playerSessions.remove(session.getId());
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public boolean isEmptyPlayer() {
        return playerSessions.isEmpty();
    }

    public int countPlayer() {
        return playerSessions.size();
    }
}
