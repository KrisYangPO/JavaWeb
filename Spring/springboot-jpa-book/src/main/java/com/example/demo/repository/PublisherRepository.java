package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	
	@Modifying
	@Query(value= "delete from publisher_book where publisher_id = :publisherId and book_id = :bookId", nativeQuery=true)
	void deleteBookFromPublisher(@Param("publisherId") Integer publisherId, @Param("bookId") Integer bookId);
	
	// Query 因為是使用 JPA 所以 Query 不能 import JDBC 要用 JPA 
	@Query("select p from Publisher p left join fetch p.books")
	List<Publisher> findAllWithBooks();
}
