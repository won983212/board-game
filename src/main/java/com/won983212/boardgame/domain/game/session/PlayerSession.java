package com.won983212.boardgame.domain.game.session;

import com.won983212.boardgame.domain.player.model.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class PlayerSession {
    private final WebSocketSession session;
    private final Player details;
    private final Long roomId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSession that = (PlayerSession) o;
        return Objects.equals(session.getId(), that.session.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(session.getId());
    }
}
