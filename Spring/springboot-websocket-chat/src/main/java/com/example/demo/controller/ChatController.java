package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.ChatMessage;

// 用來處理 webSocket 聊天訊息：支援多聊天室，根據 RoomID 可以廣播道不同聊天室。
@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate; 
	
	/* 處理來自 "/app/chat/{roomId}" 的訊息，並將訊息廣播到 "/topic/messages/{roomId}"
	 * @Param roomId 聊天室 Id
	 * @Param chatMessage 聊天訊息。
	 */
	
	@MessageMapping("/chat/{roomId}")
	public void send(@DestinationVariable String roomId, ChatMessage chatMessage) {
		messagingTemplate.convertAndSend("/topic/messages/" + roomId, chatMessage);
	}
}
