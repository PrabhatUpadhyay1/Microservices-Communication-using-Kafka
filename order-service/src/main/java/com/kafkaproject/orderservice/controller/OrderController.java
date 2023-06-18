package com.kafkaproject.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaproject.orderservice.entity.Order;
import com.kafkaproject.orderservice.service.OrderService;

@RestController
@RequestMapping("/order-service")
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping("/order")
	private Order saveorder(@RequestBody Order order) {
		return service.saveOrder(order);
	}
	@GetMapping("/order/{id}")
	private Order getOrder(@PathVariable Long id) {
		return service.getOrder(id);
	}
}
