package com.deepak.notification.service;

import org.springframework.stereotype.Service;

import com.deepak.notification.DTO.NotificationRequestDto;


@Service
public interface NotificationService {

	// without kafka 
	 void sendOrderConfirmation(NotificationRequestDto request);
	 
	 
	 // using kafka
	 void sendOrderConfirmationMessage(NotificationRequestDto request);
	 
	 
//	 //order delivered msg.
//	 void sendOrderDeliveredMessage(NotificationRequestDto request);
}
