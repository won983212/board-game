package com.won983212.boardgame.domain.game.omok;

import org.springframework.stereotype.Component;

@Component
public class OmokMessageHandler {

    public void handleHandshake(HandshakePacket packet){
        System.out.println(packet.getRoomId());
        System.out.println(packet.getAuth());
    }
}
