package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Task_FindMaxNumPublisher {

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void getMaxNumPublishers() {
		List<Publisher> publishers = publisherRepository.findAllWithBooks();
		
		OptionalInt maxPub = publishers.stream().mapToInt(p->p.getBooks().size()).max();
		if(maxPub.isEmpty()) {
			System.err.println("找不到最大值出版數。");
			return;
		}
		Integer maxPubInt = maxPub.getAsInt();
		Optional<Publisher> optMaxPublisher = publishers.stream().filter(p ->p.getBooks().size() == maxPubInt).findFirst();
		if(optMaxPublisher.isEmpty()) {
			System.err.println("找不到最大值出版商。");
			return;
		}
		Publisher maxPublisher = optMaxPublisher.get();
		System.out.printf("最大出版社名稱：%s 出版數量：%s%n", maxPublisher.getName(), maxPublisher.getBooks().size());
	}
}
