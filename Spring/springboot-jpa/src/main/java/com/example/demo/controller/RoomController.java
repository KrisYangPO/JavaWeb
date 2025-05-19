package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.dto.RoomDTO;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

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
	
//	@GetMapping
//	public String getRooms(Model model) {
//		RoomDTO roomDTO = new RoomDTO();
//		List<RoomDTO> roomDTOs = roomService.findAllRooms();
//		model.addAttribute("roomDTO",roomDTO);
//		model.addAttribute("roomDTOs",roomDTOs);
//		
//		return "room/room";
//	}
	
	// 如果不想自己先建立一個 RoomDTO roomDTO = new RoomDTO();
	// 並利用 model.addAttribute("roomDTO", roomDTO) 
	// 可以直接將 RoomDTO 當參數傳入，並給予標籤：@ModelAttribute:
	@GetMapping
	public String getRooms(Model model, @ModelAttribute RoomDTO roomDTO) {
//		// 省略：
//		RoomDTO roomDTO = new RoomDTO();
//		model.addAttribute("roomDTO",roomDTO);
//		// room.jsp 還是可以收到 RoomDTO，因為已經用 @ModelAttribute 帶入 JSP。
		
		List<RoomDTO> roomDTOs = roomService.findAllRooms();
		model.addAttribute("roomDTOs",roomDTOs);
		return "room/room";
	}
	
	// @Valid 表示這個 roomDTO 需要經過驗證。bindingResult 是驗證結果。
	// 驗證結果要放在 bindingResult 
	@PostMapping
	public String addRoom(Model model, @Valid @ModelAttribute RoomDTO roomDTO, BindingResult bindingResult) {
		// 驗證資料
		// hasError: 若有錯誤資料發生
		if(bindingResult.hasErrors()) {
			model.addAttribute("roomDTOs", roomService.findAllRooms());
			return "room/room";
		}
		
		// 進行新增。
		roomService.addRoom(roomDTO);
		return "redirect:/rooms";
	}
}
