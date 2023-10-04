package com.won983212.boardgame.domain.game.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class GameSessionService {
    private final Map<Long, GameSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, Map<SessionMetadataType, Object>> sessionMetadatas = new ConcurrentHashMap<>();

    public void joinPlayer(Long roomId, WebSocketSession session) {
        GameSession gameSession = this.sessions.get(roomId);
        if (gameSession == null) {
            gameSession = new GameSession();
            sessions.put(roomId, gameSession);
            sessionMetadatas.put(session.getId(), createInitialMetadata(roomId));
        }
        gameSession.addPlayerSession(session);
    }

    public void leavePlayer(WebSocketSession session) {
        Long roomId = getMetadata(session, SessionMetadataType.ROOM_ID);
        if (roomId == null) {
            log.warn("ROOM ID가 등록되지 않은 session이 있습니다: " + session.getId());
            return;
        }

        GameSession gameSession = this.sessions.get(roomId);
        if (gameSession != null) {
            gameSession.removePlayerSession(session);
            if (gameSession.isEmptyPlayer()) {
                sessions.remove(roomId);
            }
        }
    }

    public <T> T getMetadata(WebSocketSession session, SessionMetadataType key) {
        Map<SessionMetadataType, Object> metadata = sessionMetadatas.get(session.getId());
        if (metadata != null) {
            return (T) metadata.get(key);
        }
        return null;
    }

    public void setMetadata(WebSocketSession session, SessionMetadataType key, Object value) {
        Map<SessionMetadataType, Object> metadata = sessionMetadatas.get(session.getId());
        if (metadata == null) {
            metadata = new ConcurrentHashMap<>();
            sessionMetadatas.put(session.getId(), metadata);
        }
        metadata.put(key, value);
    }

    private static Map<SessionMetadataType, Object> createInitialMetadata(Long roomId) {
        ConcurrentHashMap<SessionMetadataType, Object> map = new ConcurrentHashMap<>();
        map.put(SessionMetadataType.ROOM_ID, roomId);
        return map;
    }
}
