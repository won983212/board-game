package com.won983212.boardgame.domain.room.repository;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.room.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class RoomRepository {
    // TODO redis로 교체: 동시성 이슈, 데이터 안정성, 확장 가능성
    private final Map<Long, Room> rooms = new HashMap<>();
    private static long nextRoomId = 1;

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

    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(rooms.get(id));
    }
}
