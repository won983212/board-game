package com.won983212.boardgame.domain.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {

	@GetMapping
	public String index() {
		return "room/list";
	}

	@PostMapping
	public void createRoom() {

	}
}
