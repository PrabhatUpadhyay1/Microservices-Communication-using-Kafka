package com.kafkaproject.orderservice.entity;

import java.io.Serializable;

import org.apache.kafka.common.serialization.Serializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class Order implements Serializer, Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "amount")
	private double amount;
	String status;
	@Override
	public byte[] serialize(String topic, Object data) {
		// TODO Auto-generated method stub
		return null;
	}
}
