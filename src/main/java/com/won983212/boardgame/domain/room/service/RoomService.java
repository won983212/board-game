package com.won983212.boardgame.domain.room.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.won983212.boardgame.domain.room.model.Room;
import com.won983212.boardgame.domain.room.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository repository;

	public Collection<Room> getRooms(){
		return repository.findAllRooms();
	}

	public void createRoom(){

	}
}
