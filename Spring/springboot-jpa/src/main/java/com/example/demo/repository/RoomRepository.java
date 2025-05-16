package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.Room;

// Spring JPA
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	// Room: Entity; Integer: @Id 主鍵的型別。
	
}
