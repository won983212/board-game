package com.won983212.boardgame.domain.room.service;

import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;

    public Collection<Room> getRooms() {
        return repository.findAllRooms();
    }
}
