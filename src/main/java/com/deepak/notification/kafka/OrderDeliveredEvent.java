package com.deepak.notification.kafka;

import com.deepak.notification.DTO.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveredEvent {

	private Long orderId;
	private String orderNumber;
	private Long userId;
	private Double amount;
	private String paymentMethod;
	private PaymentStatus paymentStatus;

}
