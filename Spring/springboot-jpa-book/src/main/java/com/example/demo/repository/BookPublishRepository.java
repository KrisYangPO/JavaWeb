package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.BookPublish;

@Repository
public interface BookPublishRepository extends JpaRepository<BookPublish, Integer>{
	
	@EntityGraph(attributePaths = {"book", "publisher"})
	@Query("SELECT p FROM BookPublish p")
	List<BookPublish> findAllWithBookAndPublisher();
}
