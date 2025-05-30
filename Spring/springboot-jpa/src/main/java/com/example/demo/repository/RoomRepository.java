package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.Room;



// Spring JPA
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	// Room: Entity; Integer: @Id 主鍵的型別。
	// 預設會實現 CRUD，但自訂義需要額外設計。
	
	
	// 自訂義查詢：JPA 會根據你在方法命名當中的 "關鍵字" 定義你的方法
	// 1. 查詢 roomsize 大於指定值的房間
	List<Room> findByRoomSizeGreaterThan(Integer roomSize);
	
	// 2. 查詢 roomSize 大於指定值的房間 (自行撰寫 T-SQL)
	// 欄位名稱要符合資料表中的設定。
//	@Query(value ="select room_id, room_name, room_size from room where room_size > : roomSize", nativeQuery = true)
//	List<Room> findRooms(Integer roomSize);
	
	@Query(
			value="select room_id, room_name, room_size from room where room_size > :roomSize ",
			 nativeQuery = true
		)
	List<Room> findRoomLargerThan(Integer roomSize);
	
	// 3. 查詢 roomSize 大於指定值的房間 (自行撰寫 PQL)
	// 欄位名稱也可以符合 Entity 中的屬性設定。
	// Room r 並不是 room 資料表，更像是指 Room entity 物件，後面呼叫 getter r.roomSize。
	@Query(value ="select r from Room r where r.roomSize > :roomSize")
	List<Room> readRooms(Integer roomSize);
}
