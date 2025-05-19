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
 * GET: /rooms -> 查詢所有會議室(多筆)
 * POST: /room -> 新增會議室
 * POST: /room/update/{roomId} -> 完整修改會議室(同時修改 roomName 與 roomSize)
 * GET: /room/delete/{roomId} -> 刪除會議室
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
