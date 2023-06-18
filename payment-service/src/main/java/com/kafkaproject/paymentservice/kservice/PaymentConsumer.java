package com.kafkaproject.paymentservice.kservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaproject.paymentservice.dto.Order;
import com.kafkaproject.paymentservice.entity.Payment;
import com.kafkaproject.paymentservice.repository.PaymentRepository;
import com.kafkaproject.paymentservice.service.PaymentService;

import lombok.Builder;

@Service
public class PaymentConsumer {

	
	@Autowired
	PaymentService service;
	
	@Autowired
	PaymentProducer paymentProducer;
	
	@KafkaListener(
	        topics = "order",
	        groupId = "payment",
	        containerFactory = "kafkaListenerContainerFactory"
	    )
	public void consume(String message) {
		System.out.println("message received" + message);
        ObjectMapper objectMapper = new ObjectMapper();
		try {
            Order order = objectMapper.readValue(message, Order.class);
            // Process the order object as needed
            System.out.println("Received order: " + order);
            if (order.getStatus().equals("CREATED")) {
            	
				Payment payment = service.savePayment(Payment.builder()
						.paymentType("UPI")
						.amount(1000)
						.status("Successful")
						.build());
				paymentProducer.sendMessage(payment);
			}
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}
}
