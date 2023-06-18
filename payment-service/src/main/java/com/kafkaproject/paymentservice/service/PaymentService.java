package com.kafkaproject.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafkaproject.paymentservice.entity.Payment;
import com.kafkaproject.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository repository;
	
	public Payment savePayment(Payment payment) {
		repository.save(payment);
		return payment;
	}
}
