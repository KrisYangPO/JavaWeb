package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.dto.RoomDTO;
import com.example.demo.service.RoomService;

/*
 * GET: /room/{roomId} -> 查詢指定會議室(單筆)
 * GET: /rooms -> 
 * POST: /room
 * POST: /room/update/{roomId}
 * GET: /room/delete/{roomId}
 * */

@Controller
@RequestMapping(value = {"/room", "/rooms"})
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getRooms(Model model) {
		RoomDTO roomDTO = new RoomDTO();
		List<RoomDTO> roomDTOs = roomService.findAllRooms();
		model.addAttribute("roomDTO",roomDTO);
		model.addAttribute("roomDTOs",roomDTOs);
		
		return "room/room";
	}
}
