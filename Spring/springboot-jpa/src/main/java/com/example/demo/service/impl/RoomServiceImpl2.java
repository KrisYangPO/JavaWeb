package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RoomAlreadyExistException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDTO;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;

@Service
public class RoomServiceImpl2 implements RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomMapper roomMapper;

	@Override
	public List<RoomDTO> findAllRooms() {
		return roomRepository.findAll().stream().map(roomMapper::toDTO).toList();
	}

	@Override
	public RoomDTO getRoomById(Integer roomId) {
		Optional<Room> room = roomRepository.findById(roomId);
		if(room.isEmpty()) {
			throw new RoomNotFoundException("尋找房間失敗啦！查無此編號：" + roomId);
		}
		// 將 Optional<room> 轉成 Room 物件後，轉成 RoomDTO
		return roomMapper.toDTO(room.get());
	}

	@Override
	public void addRoom(RoomDTO roomDTO) {
		
		// 如果這個 roomDTO 的編號已經存在 repository 就不能新增。
		Optional<Room> room = roomRepository.findById(roomDTO.getRoomId());
		if(room.isPresent()) {
			throw new RoomAlreadyExistException("新增失敗啦！房間已存在：" + room.get().getRoomName());
		}
		
		// 執行新增：
		roomRepository.save(roomMapper.toEntity(roomDTO));
	}

	
	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
			
		// 建立 RoomDTO 物件
		// 之所以要用 RoomDTO 物件，是因為要用上面的 Service::addRoom() 方法，
		// 下面就直接執行 Service 的新增方法
		RoomDTO newRoom = new RoomDTO(roomId, roomName, roomSize);
		
		// 用 Service 方法新增 Room，
		// 因為上面的 addRoom 已經確認過是否有重複 room，
		// 這個 addRoom 方法覆寫就不用做確認。
		addRoom(newRoom);
	}

	
	@Override
	public void updateRoom(Integer roomId, RoomDTO roomDTO) {
		// 確認可以查詢得到 roomDTO 
		RoomDTO room = getRoomById(roomId);
		if(room == null) {
			throw new RoomAlreadyExistException("更新失敗啦！房間不存在。");
		}
		
		// 將 roomDTO 轉 room Entity 
		Room updatedRoom = roomMapper.toEntity(roomDTO);
		roomRepository.save(updatedRoom);
	}

	
	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
//		// 因為下面直接執行上面 Service 的 updateRoom，所以這粒不用重複檢查
//		// 確認可以查詢得到 roomDTO 
//		RoomDTO room = getRoomById(roomId);
//		if(room == null) {
//			throw new RoomAlreadyExistException("更新失敗，房間不存在。");
//		}
		
		// 建立 RoomDTO 物件，直接使用上面的 updateRoom
		RoomDTO roomDTO = new RoomDTO(roomId, roomName, roomSize);
		
		// 使用上面 Service 的 updateRoom:
		updateRoom(roomId, roomDTO);
	}

	
	@Override
	public void deleteRoom(Integer roomId) {
		// 確認可以查詢得到 roomDTO 
		RoomDTO room = getRoomById(roomId);
		if(room == null) {
			throw new RoomAlreadyExistException("刪除失敗啦！沒有這個房間。");
		}
		
		roomRepository.delete(roomMapper.toEntity(room));
	}

	
}
