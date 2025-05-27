package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	
	@Modifying
	@Query(value= "delete from publisher_book where publisher_id = :publisherId and book_id = :bookId", nativeQuery=true)
	void deleteBookFromPublisher(@Param("publisherId") Integer publisherId, @Param("bookId") Integer bookId);
	
	// 查詢 Publisher 資訊，需要取得這個 Publisher 的出版數 books，
	// 是 ManyToMany fetchType = LAZY，因此可能會觸發 LAZY 查詢或是 N+1查詢問題
	// 所以直接將 publisher 與他的關聯表格 book 組合再一起，
	// 在查詢方法執行時，SQL 抓取這兩個關聯資料時會組合，在一次查詢內呈現。
	
	@Query("select a from Publisher a left join fetch a.books")
	List<Publisher> findAllWithBooks();
}
