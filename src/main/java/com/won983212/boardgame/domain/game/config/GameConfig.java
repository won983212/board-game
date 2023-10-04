package com.won983212.boardgame.domain.game.config;

import com.won983212.boardgame.domain.game.packet.PacketManager;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GameConfig {
    private final List<GameConfigurer> gameConfigurers;
    private final PacketManager packetManager;

    @PostConstruct
    public void onPostConstruct() {
        for (GameConfigurer configurer : gameConfigurers) {
            PacketRegistry registry = new PacketRegistry(packetManager);
            configurer.registerPackets(registry);
        }
    }
}
