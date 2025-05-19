package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.RoomDTO;

public interface RoomService {
	public List<RoomDTO> findAllRooms();		// 查詢所有房間
	public RoomDTO getRoomById(Integer roomId); // 查詢單筆房間
	public void addRoom(RoomDTO roomDTO);		// 新增房間
	public void addRoom(Integer roomId, String roomName, Integer roomSize); // 新增房間
	public void updateRoom(Integer roomId, RoomDTO roomDTO); // 修改房間。
	public void updateRoom(Integer roomId, String roomName, Integer roomSize);
	public void deleteRoom(Integer roomId); // 刪除房間。
}
