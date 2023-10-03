package com.won983212.boardgame.domain.game.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameSessionService {
    private final Map<Long, Map<String, WebSocketSession>> sessions = new ConcurrentHashMap<>();

    public void addSession(Long roomId, WebSocketSession session) {
        Map<String, WebSocketSession> roomSessions = this.sessions.get(roomId);
        if (roomSessions == null) {
            roomSessions = new ConcurrentHashMap<>();
            sessions.put(roomId, roomSessions);
        }
        roomSessions.put(session.getId(), session);
    }

    public void removeSession(Long roomId, WebSocketSession session) {
        Map<String, WebSocketSession> roomSessions = this.sessions.get(roomId);
        if (roomSessions != null) {
            roomSessions.remove(session.getId());
        }
    }
}
