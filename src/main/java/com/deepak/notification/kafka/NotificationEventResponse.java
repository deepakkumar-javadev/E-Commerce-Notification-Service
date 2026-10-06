package com.deepak.notification.kafka;

import com.deepak.notification.DTO.PaymentStatus;

import lombok.Data;

@Data
public class NotificationEventResponse {

	private Long orderId;
	private String orderNumber;
	private Long userId;
	private String email;
	private Double amount;
	private String paymentMethod;
	private PaymentStatus paymentStatus;
	private String inventoryStatus;
	
}
