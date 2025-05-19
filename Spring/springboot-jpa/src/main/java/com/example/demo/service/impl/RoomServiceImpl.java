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
public class RoomServiceImpl implements RoomService{
	
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
		Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("找不到會議室：roomId = " + roomId));
		return roomMapper.toDTO(room);
	}

	@Override
	public void addRoom(RoomDTO roomDTO) {
		// 判斷房號是否存在
		Optional<Room> optRoom = roomRepository.findById(roomDTO.getRoomId());
		if(optRoom.isPresent()) {
			throw new RoomAlreadyExistException(String.format("新增失敗：房號 %s 已經存在。", roomDTO.getRoomId()));
		}
		// 進入新增程序。
		Room room = roomMapper.toEntity(roomDTO);
		
		// 將 Entity room 存入
		// 方法結束後，save(room) 才會將 room 才會寫入資料庫，如果前面失敗就會回滾 (前面輸入的就撤回，單筆資料表沒差)
		// 方法結束後會自動執行 "flush" save(room) 動作遇到 flush 才會將資料寫入資料庫。
		// save(room) 類似暫存，flush() 方法像是觸發儲存的板機。
		roomRepository.save(room);
		
		// 如果後續再寫 roomRepository.flush()
		// 就是在方法結束前，將 save(room) 手動將資料寫入資料庫。
	}

	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDTO roomDTO = new RoomDTO(roomId, roomName, roomSize);
		addRoom(roomDTO);
	}

	
	@Override
	public void updateRoom(Integer roomId, RoomDTO roomDTO) {
		// 判斷房號是否存在
		Optional<Room> optRoom = roomRepository.findById(roomId);
		// 如果房間不存在，就不可以 update
		if(optRoom.isEmpty()) {
			throw new RoomAlreadyExistException(String.format("新增失敗：房號 %s 已經存在。", roomId));
		}
		roomDTO.setRoomId(roomId);
		Room room = roomMapper.toEntity(roomDTO);
		// JPA 自動實作的 CRUD 方法：saveAndFlush 可以當作 update 更新方法。
		// 馬上強制寫入更新。
		roomRepository.saveAndFlush(room);
	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDTO roomDTO = new RoomDTO(roomId, roomName, roomSize);
		updateRoom(roomId, roomDTO);
		
	}

	@Override
	public void deleteRoom(Integer roomId) {
		// 判斷該房號是否已經存在 ?
		Optional<Room> optRoom = roomRepository.findById(roomId);
		if(optRoom.isEmpty()) { // 房間不存在
			throw new RoomAlreadyExistException("刪除失敗: 房號 " + roomId + " 不存在");
		}
		roomRepository.deleteById(roomId);
	}
	
	
}
