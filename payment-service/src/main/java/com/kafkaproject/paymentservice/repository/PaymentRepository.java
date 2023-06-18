package com.kafkaproject.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kafkaproject.paymentservice.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
