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
    private final Map<String, PlayerSession> playerSessions = new ConcurrentHashMap<>();

    public void joinPlayer(Long roomId, Player player, WebSocketSession session) {
        Optional<Room> oRoom = roomService.findById(roomId);
        if (oRoom.isEmpty()) {
            log.warn("{}가 없는 roomId를 전송했습니다: {}", session.getId(), roomId);
            return;
        }

        GameContext context = oRoom.get().getGameType().createContext();
        GameSession gameSession = this.sessions.get(roomId);
        if (gameSession == null) {
            gameSession = new GameSession(context);
            sessions.put(roomId, gameSession);
        }

        PlayerSession playerSession = new PlayerSession(session, player, roomId);
        playerSessions.put(session.getId(), playerSession);
        gameSession.addPlayerSession(playerSession);
    }

    public void leavePlayer(WebSocketSession session) {
        PlayerSession playerSession = playerSessions.get(session.getId());
        if (playerSession == null) {
            log.warn("등록되지 않은 session이 있습니다: " + session.getId());
            return;
        }

        Long roomId = playerSession.getRoomId();
        GameSession gameSession = this.sessions.get(roomId);

        if (gameSession != null) {
            gameSession.removePlayerSession(playerSession);
            if (gameSession.isEmptyPlayer()) {
                sessions.remove(roomId);
                roomService.removeRoom(roomId);
            }
        }
    }

    public GameSession getGameSession(Long roomId) {
        return sessions.get(roomId);
    }
}
