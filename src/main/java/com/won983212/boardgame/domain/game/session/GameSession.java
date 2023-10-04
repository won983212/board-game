package com.won983212.boardgame.domain.game.session;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class GameSession {
    private final Map<String, PlayerSession> playerSessions = new ConcurrentHashMap<>();
    private final GameContext gameContext;

    public void addPlayerSession(PlayerSession session) {
        playerSessions.put(session.getSession().getId(), session);
    }

    public void removePlayerSession(PlayerSession session) {
        playerSessions.remove(session.getSession().getId());
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
