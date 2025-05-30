package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;

@SpringBootTest
public class RoomRead {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	public void readRoom() {
		List<Room> rooms = roomRepository.findRoomLargerThan(150);
		
		System.out.println("目標房數：" + rooms.size());
		System.out.println("房間資訊：" + rooms);
	}
}
