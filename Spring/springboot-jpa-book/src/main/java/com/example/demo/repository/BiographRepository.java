package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Biography;

@Repository
public interface BiographRepository extends JpaRepository<Biography, Integer>{
	
//	@Query("select b from Biography b inner join fetch b.author") // biography 與 author 同時都有，沒有同時存在就不顯示。
	/*
	 * A  1
	 * B  2
	 * D  4
	 * */
	@Query("select b from Biography b left join fetch b.author") // 若沒有查到 author 則顯示 null。
	/*
	 * A  1
	 * B  2
	 * C  null
	 * D  4
	 * 
	 * */
	
	List<Biography> findAllwithAuthor();
	
	
	
}
