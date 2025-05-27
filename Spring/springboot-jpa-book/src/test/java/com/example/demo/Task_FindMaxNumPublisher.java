package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.PublisherRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Task_FindMaxNumPublisher {

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	@Transactional
	public void getMaxNumPublishers() {
		// 找所有出版社
		List<Publisher> publishers = publisherRepository.findAllWithBooks();
//		
//		// 找最大出版數值
//		OptionalInt maxPub = publishers
//								.stream()
//								.mapToInt(p->p.getBooks().size())
//								.max();
//		if(maxPub.isEmpty()) {
//			System.err.println("找不到最大值出版數。");
//			return;
//		}
//		
//		
//		// 找出具有最大出版數量的出版社
//		int maxPubInt = maxPub.getAsInt();
//		Optional<Publisher> optMaxPublisher = publishers
//											.stream()
//											.filter(p ->p.getBooks().size() == maxPubInt)
//											.findFirst();
//		if(optMaxPublisher.isEmpty()) {
//			System.err.println("找不到最大值出版商。");
//			return;
//		}
//		
//		// optional 取得最大出版社，顯示結果。
//		Publisher maxPublisher = optMaxPublisher.get();
//		System.out.printf("最大出版社名稱：%s 出版數量：%s%n", maxPublisher.getName(), maxPublisher.getBooks().size());
	}
}
