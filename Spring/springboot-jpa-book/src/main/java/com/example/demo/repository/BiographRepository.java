package com.example.demo.repository;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Biography;

@Repository
public interface BiographRepository extends JpaRepository<Biography, Integer>{
	@Query("select b from Biography b left join fetch b.author") // 若沒有查到 author 則顯示 null。
	List<Biography> findAllwithAuthor();
}
