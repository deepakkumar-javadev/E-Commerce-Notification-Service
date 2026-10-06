package com.deepak.notification.service;

import org.springframework.stereotype.Service;

import com.deepak.notification.DTO.NotificationRequestDto;
import com.deepak.notification.DTO.PaymentStatus;
import com.deepak.notification.DTO.UserResponse;
import com.deepak.notification.Notification.EmailNotificationService;

import com.deepak.notification.client.userClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final EmailNotificationService emailNotificationService;

	 
	 private final userClient userclient;

	 //***** future-Requirement *****//
	//private final SmsNotificationService smsNotificationService;
	// private final PushService pushservice ;

	@Override
	public void sendOrderConfirmation( NotificationRequestDto request) {

		
		if (PaymentStatus.PAID == request.getPaymentStatus()) {

			UserResponse user = userclient.getUser(request.getUserId());
			
			emailNotificationService.sendOrderConfirmationEmail(user,request);
			
			//smsNotificationService.sendOrderConfirmationSms(request.getPhoneNumber(), request.getOrderId());
			
			
			
		}
		
	}

	// send notification using kafka  for COD  | ONLINE 
	@Override
	public void sendOrderConfirmationMessage( NotificationRequestDto request) {
		

			UserResponse user = userclient.getUser(request.getUserId());
	
			
			emailNotificationService.sendOrderConfirmationEmailNotify(user, request);
			
			//smsNotificationService.sendOrderConfirmationSms(request.getPhoneNumber(), request.getOrderId());
			
			
			
		
		
	}

	
	
	
	
	
	

}
