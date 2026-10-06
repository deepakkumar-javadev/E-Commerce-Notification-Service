package com.deepak.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.deepak.notification.DTO.NotificationRequestDto;
import com.deepak.notification.DTO.UserResponse;
import com.deepak.notification.DTO.orderResponseDto;
import com.deepak.notification.client.orderClient;
import com.deepak.notification.client.userClient;
import com.deepak.notification.entity.NotificationType;
import com.deepak.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationEventConsumer {

	private final NotificationService notificationService;
	private final userClient userclient;
	private final orderClient orderclient;

	// COD

	@KafkaListener(topics = "inventory.updated", groupId = "notification-service",containerFactory = "notificationEventKafkaListenerContainerFactory")
	public void consumeOrderCreatedMessage(NotificationEventResponse event) {

		NotificationRequestDto request = new NotificationRequestDto();
		request.setOrderId(event.getOrderId());
		request.setUserId(event.getUserId());
		request.setAmount(event.getAmount());
		request.setPaymentMethod(event.getPaymentMethod());
		request.setPaymentStatus(event.getPaymentStatus());

		notificationService.sendOrderConfirmationMessage(request);
	}

	@KafkaListener(topics = "inventory.reserved", groupId = "notification-service",containerFactory = "orderReservedKafkaListenerContainerFactory")
	public void consumeOrderCreatedMessage(OrderReservedEvent event) {

		orderResponseDto order = orderclient.getOrderById(event.getOrderId());

		NotificationRequestDto request = new NotificationRequestDto();
		request.setOrderId(order.getOrderId());
		request.setUserId(order.getUserId());
		request.setAmount(order.getTotalAmount());
		request.setPaymentMethod(order.getPaymentMethod());
		request.setPaymentStatus(order.getPaymentStatus());
		request.setNotificationType(NotificationType.ORDER_CONFIRMED);
		
		UserResponse user = userclient.getUser(request.getUserId());
		request.setEmail(user.getEmail());

		notificationService.sendOrderConfirmationMessage(request);
	}

	// DELIVERED
	@KafkaListener(topics = "order.delivered", groupId = "notification-service", containerFactory = "orderDeliveredKafkaListenerContainerFactory")
	public void consumeOrderDeliveredMessage(OrderDeliveredEvent event) {

		System.out.println("===== ORDER.DELIVERED CONSUMED =====");
	    System.out.println("Order ID: " + event.getOrderId());
	    System.out.println("User ID: " + event.getUserId());
	    System.out.println("Payment Method: " + event.getPaymentMethod());
	    System.out.println("Payment Status: " + event.getPaymentStatus());
		
	    
	    NotificationRequestDto request = new NotificationRequestDto();

		request.setOrderId(event.getOrderId());
		request.setUserId(event.getUserId());
		request.setAmount(event.getAmount());
		request.setPaymentMethod(event.getPaymentMethod());
		request.setPaymentStatus(event.getPaymentStatus());
		request.setNotificationType(NotificationType.ORDER_DELIVERED);
		
		UserResponse user = userclient.getUser(request.getUserId());

		request.setEmail(user.getEmail());

		notificationService.sendOrderConfirmationMessage(request);
	}
}
