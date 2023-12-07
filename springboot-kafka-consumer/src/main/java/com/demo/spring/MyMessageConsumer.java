package com.demo.spring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyMessageConsumer {

	@KafkaListener(topics = "demo-topic", groupId = "g1")
	public void onMessage(String message) {
		System.out.println("Received : "+message);
	}
}
