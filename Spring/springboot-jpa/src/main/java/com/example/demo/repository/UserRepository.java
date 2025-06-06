package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "select user_id, username, password_hash, salt, email, active, role from users where username=:username", nativeQuery = true)
	User getUser(String username);
	
	@Query(value = "select u from User u where username=:username")
	Optional<User> readUserByName(String username);
}
