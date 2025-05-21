package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.RoomDTO;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Room;
import com.example.demo.model.entity.User;

@Component // 此物件由 SpringBoot 來管理 (@Service(子), @Repository(子), @Component(父) 都是由 SpringBoot 管)
public class UserMapper {
	
	@Autowired
	private ModelMapper modelMapper; 
	
	public UserDto toDTO(User user) {
		// Entity 2 DTO
		return modelMapper.map(user, UserDto.class);
	}
	
	public User toEntity(UserDto userDto) {
		// DTO 轉 Entity
		return modelMapper.map(userDto, User.class);
	}
}
