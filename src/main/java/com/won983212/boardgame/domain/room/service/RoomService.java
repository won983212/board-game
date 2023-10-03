package com.won983212.boardgame.domain.room.service;

import com.won983212.boardgame.domain.game.enums.GameType;
import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;

    public Collection<Room> getRooms() {
        return repository.findAllRooms();
    }

    public Optional<Room> findById(Long id) {
        return repository.findById(id);
    }

    public Room createRoom(String roomName, GameType type, Long masterId) {
        Room room = Room.of(roomName, type, masterId);
        return repository.save(room);
    }
}
