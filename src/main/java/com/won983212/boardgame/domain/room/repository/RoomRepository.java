package com.won983212.boardgame.domain.room.repository;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.room.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoomRepository {
    // TODO redis로 교체: 동시성 이슈, 데이터 안정성, 확장 가능성
    // TODO Test data
    private final Map<Long, Room> rooms = new HashMap<>(Map.of(
            1L, Room.of("초보만", GameType.OMOK, 1L).withId(1L),
            2L, Room.of("안녕하세요", GameType.OMOK, 2L).withId(2L)
    ));
    private static long nextRoomId = 3;

    public Collection<Room> findAllRooms() {
        return rooms.values();
    }

    public Room save(Room room) {
        long id = nextRoomId++;
        Room r = room.withId(id);
        rooms.put(id, r);
        return r;
    }

    public void delete(Long roomId) {
        rooms.remove(roomId);
    }
}
