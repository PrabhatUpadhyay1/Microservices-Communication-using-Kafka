package com.kafkaproject.paymentservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order{


	private Long id;
	private String name;
	private int quantity;
	private double amount;
	private String status;
}
