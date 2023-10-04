package com.won983212.boardgame.domain.game.enums;

import com.won983212.boardgame.domain.game.exception.CannotCreateContextException;
import com.won983212.boardgame.domain.game.omok.OmokContext;
import com.won983212.boardgame.domain.game.session.GameContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;

/**
 * Enum 이름을 template html파일 이름과 동일하게 설정해야합니다.
 * 이름은 모두 소문자로 변환되므로 camel-case를 사용할 수 없습니다.
 */
@Getter
@RequiredArgsConstructor
public enum GameType {
    OMOK("오목", 2, OmokContext.class);

    private final String label;
    private final int maxPlayers;
    private final Class<? extends GameContext> contextClass;

    public GameContext createContext() {
        try {
            return contextClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new CannotCreateContextException(e);
        }
    }
}
