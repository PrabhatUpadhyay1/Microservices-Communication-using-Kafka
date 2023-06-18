package com.kafkaproject.deliveryservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeliveryConsumer {

	
	@KafkaListener(
	        topics = "payment",
	        groupId = "delivery",
	        containerFactory = "kafkaListenerContainerFactory"
	    )
	public void consume(String message) {
		System.out.println("message receive " +message);
	}
}
