package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 負責啟用 STOMP 協定，並設定路由規則。
@Configuration
@EnableWebSocketMessageBroker //啟用 STOMP WebSocket 支援。
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	// 註冊 STOMP 端點，前端可以透過此端點建立 WebSocket 連線
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket").withSockJS(); // .withSockJS()應付瀏覽器相容性
	}

	// 設定訊息代理，定義訊息如何發送與接收
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 可以處理所有 /topic 開頭的訊息。
		registry.enableSimpleBroker("/topic");
		
		// 讓前端可以傳送到 /app 開頭的訊息會被路由到 @MessageMapping
		// ex: 前端針對 /app/chat/{roomId} 發送訊息，該訊息會廣播道 /topic/messages/{roomId}
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
