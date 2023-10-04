package com.won983212.boardgame.domain.game.omok;

import com.won983212.boardgame.domain.game.config.GameConfigurer;
import com.won983212.boardgame.domain.game.config.PacketRegistry;
import org.springframework.stereotype.Component;

@Component
public class OmokConfig implements GameConfigurer {

    @Override
    public void registerPackets(PacketRegistry registry) {
    }
}
