package com.deepak.notification.Notification;

import org.springframework.stereotype.Service;

@Service

public class SmsNotificationService {

	//private final SmsServiceProvider smsservice;
	
	public void sendOrderConfirmationSms(String phone, Long orderId) {

        System.out.println("===== SMS NOTIFICATION =====");
        System.out.println("Phone: " + phone);
        System.out.println("Order ID: " + orderId);
        
        
        
        
        //smsservice.send(phone,orderId);
        
        
        System.out.println("Message: Your Macy's order has been confirmed.");
        
        
        
    }
	
}
