package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.RoomDTO;
import com.example.demo.model.entity.Room;

@Component // 此物件由 SpringBoot 來管理 (@Service(子), @Repository(子), @Component(父) 都是由 SpringBoot 管)
public class RoomMapper {
	
	@Autowired
	private ModelMapper modelMapper; 
	
	public RoomDTO toDTO(Room room) {
		// Entity 2 DTO
		return modelMapper.map(room, RoomDTO.class);
	}
	
	public Room toEntity(RoomDTO roomDTO) {
		// DTO 轉 Entity
		return modelMapper.map(roomDTO, Room.class);
	}
}
