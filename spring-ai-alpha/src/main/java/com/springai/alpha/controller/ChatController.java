package com.springai.alpha.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	private final ChatClient chatClient;

	public ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@GetMapping("/hello-ai")
	public String helloAI() {
		return chatClient.prompt("Who are you?").call().content();
	}

	@GetMapping("/ask")
	public String ask(@RequestParam("question") String question) {
	    return chatClient.prompt(question)
	            .call()
	            .content();
	}
}
