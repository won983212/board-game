package com.won983212.boardgame.domain.game.config;

import com.won983212.boardgame.domain.game.exception.NotFoundPlayerException;
import com.won983212.boardgame.domain.game.omok.HandshakePacket;
import com.won983212.boardgame.domain.game.session.GameSessionService;
import com.won983212.boardgame.domain.player.model.Player;
import com.won983212.boardgame.domain.player.service.PlayerService;
import com.won983212.boardgame.global.security.AppAuthentication;
import com.won983212.boardgame.global.security.AuthenticationProvider;
import com.won983212.boardgame.global.security.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultGameMessageHandler {

    private final GameSessionService gameSessionService;
    private final AuthenticationProvider authenticationProvider;
    private final PlayerService playerService;

    public void handleHandshake(WebSocketSession session, HandshakePacket packet) throws IOException {
        try {
            AppAuthentication auth = authenticationProvider.getAuthenticationDetail(AuthenticationToken.of(packet.getAuth()));
            Player player = playerService.findById(auth.getUserId())
                    .orElseThrow(() -> new NotFoundPlayerException("Handshake에서 찾을 수 없는 playerId를 보냈습니다: " + auth.getUserId()));
            gameSessionService.joinPlayer(packet.getRoomId(), player, session);
        } catch (Throwable t) {
            // TODO master가 접속하지 못하면 room을 삭제해야 한다.
            session.close();
        }
    }
}
