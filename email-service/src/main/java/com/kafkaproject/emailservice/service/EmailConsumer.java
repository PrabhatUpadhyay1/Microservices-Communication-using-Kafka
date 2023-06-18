package com.kafkaproject.emailservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

	@KafkaListener(
	        topics = "payment",
	        groupId = "email",
	        containerFactory = "kafkaListenerContainerFactory"
	    )
	public void consume(String message) {
		System.out.println("message receive " +message);
	}
}
