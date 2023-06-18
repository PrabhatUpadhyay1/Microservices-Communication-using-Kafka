package com.kafkaproject.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafkaproject.orderservice.entity.Order;
import com.kafkaproject.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	@Autowired
	OrderProducer producer;
	
	public Order saveOrder(Order order) {
		Order res = repository.save(order);
		producer.sendMessage(res);
		System.out.println("order sent" +  res);
		return res;
	}
	public Order getOrder(Long id) {
		return repository.findById(id).get();
	}
}
