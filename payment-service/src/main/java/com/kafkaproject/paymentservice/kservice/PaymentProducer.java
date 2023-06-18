package com.kafkaproject.paymentservice.kservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kafkaproject.paymentservice.entity.Payment;

@Service
public class PaymentProducer {

		
	@Autowired
	NewTopic topic;
	@Autowired
	KafkaTemplate<String, Payment> kafkaTemplate;
	
	public void sendMessage(Payment payment) {

		Message<Payment> message = MessageBuilder
				.withPayload(payment)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
		
	}
	
}
