package com.won983212.boardgame.domain.game.session;

import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameSessionService {
    private final RoomService roomService;
    private final Map<Long, GameSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, Map<SessionMetadataType, Object>> sessionMetadatas = new ConcurrentHashMap<>();

    public void joinPlayer(Long roomId, Player player, WebSocketSession session) {
        Optional<Room> room = roomService.findById(roomId);
        if (room.isEmpty()) {
            log.warn("{}가 없는 roomId를 전송했습니다: {}", session.getId(), roomId);
            return;
        }

        GameSession gameSession = this.sessions.get(roomId);
        if (gameSession == null) {
            gameSession = new GameSession();
            sessions.put(roomId, gameSession);
            sessionMetadatas.put(session.getId(), createInitialMetadata(roomId, player));
        }

        gameSession.addPlayerSession(session);
    }

    public void leavePlayer(WebSocketSession session) {
        Long roomId = getPlayerMetadata(session, SessionMetadataType.ROOM_ID);
        if (roomId == null) {
            log.warn("ROOM ID가 등록되지 않은 session이 있습니다: " + session.getId());
            return;
        }

        GameSession gameSession = this.sessions.get(roomId);
        if (gameSession != null) {
            gameSession.removePlayerSession(session);
            if (gameSession.isEmptyPlayer()) {
                sessions.remove(roomId);
                roomService.removeRoom(roomId);
            }
        }
    }

    public <T> T getPlayerMetadata(WebSocketSession session, SessionMetadataType key) {
        Map<SessionMetadataType, Object> metadata = sessionMetadatas.get(session.getId());
        if (metadata != null) {
            return (T) metadata.get(key);
        }
        return null;
    }

    public void setPlayerMetadata(WebSocketSession session, SessionMetadataType key, Object value) {
        Map<SessionMetadataType, Object> metadata = sessionMetadatas.get(session.getId());
        if (metadata == null) {
            metadata = new ConcurrentHashMap<>();
            sessionMetadatas.put(session.getId(), metadata);
        }
        metadata.put(key, value);
    }

    public GameSession getGameSession(Long roomId) {
        return sessions.get(roomId);
    }

    private static Map<SessionMetadataType, Object> createInitialMetadata(Long roomId, Player player) {
        ConcurrentHashMap<SessionMetadataType, Object> map = new ConcurrentHashMap<>();
        map.put(SessionMetadataType.ROOM_ID, roomId);
        map.put(SessionMetadataType.PLAYER_INFO, player);
        return map;
    }
}
