package com.kafkaproject.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kafkaproject.orderservice.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
