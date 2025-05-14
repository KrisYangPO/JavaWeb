package com.example.demo.config;

import java.beans.BeanProperty;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {
	
	// @Bean 說明此物件被Spring管理，其他程式可以透過 @Autowired 自動綁定起來取的該物件 (不需要 new)
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
	
}
