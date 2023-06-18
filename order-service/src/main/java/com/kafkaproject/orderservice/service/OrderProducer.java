package com.kafkaproject.orderservice.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.kafkaproject.orderservice.entity.Order;

@Component
public class OrderProducer {

	@Autowired
	NewTopic topic;
	
	@Autowired
	KafkaTemplate<String, Order> kafkaTemplate;
	
	public void sendMessage(Order order) {

		Message<Order> message = MessageBuilder
				.withPayload(order)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
	}
}
