package com.deepak.notification.Notification;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.deepak.notification.DTO.NotificationRequestDto;
import com.deepak.notification.DTO.UserResponse;
import com.deepak.notification.entity.Notification;
import com.deepak.notification.entity.NotificationChannel;
import com.deepak.notification.entity.NotificationStatus;
import com.deepak.notification.entity.NotificationType;
import com.deepak.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {

	private final NotificationRepository notificationRepository;
	 private final JavaMailSender mailSender;

	 
	 //# order confirmation msg 
	    public void sendOrderConfirmationEmail(UserResponse user,NotificationRequestDto request) {

	        SimpleMailMessage message = new SimpleMailMessage();

	        message.setTo(user.getEmail());

	        message.setSubject("Ecom Order Confirmation");

	        message.setText(
	                "Hello,\n\n" +
	                "Your order has been successfully confirmed.\n\n" +
	                "Order ID: " + request.getOrderId() + "\n" +
	                "Amount: ₹" + request.getAmount() + "\n" +
	                "Payment Method: " + request.getPaymentMethod() + "\n" +
	                "Payment Status: " + request.getPaymentStatus() + "\n\n" +
	                "Thank you for shopping with Ecom.com."
	        );

	        mailSender.send(message);
	    }
	    
	    
	    
	    //# order delivered msg.
	    
	    public void sendOrderConfirmationEmailNotify(
	            UserResponse user,
	            NotificationRequestDto request) {

	        SimpleMailMessage message = new SimpleMailMessage();

	        message.setTo(user.getEmail());

	        // ==============================
	        // EMAIL TYPE CHECK
	        // ==============================

	        if (request.getNotificationType() == NotificationType.ORDER_DELIVERED) {

	            // DELIVERED EMAIL
	            message.setSubject("Ecom.com Order Delivered");

	            message.setText(
	                    "Hello " + user.getName() + ",\n\n" +
	                    "Your order has been successfully delivered.\n\n" +
	                    "Order ID: " + request.getOrderId() + "\n" +
	                    "Amount: ₹" + request.getAmount() + "\n" +
	                    "Payment Method: " + request.getPaymentMethod() + "\n" +
	                    "Payment Status: " + request.getPaymentStatus() + "\n\n" +
	                    "Thank you for shopping with Ecom.com."
	            );

	        } else {

	            // CONFIRMATION EMAIL
	            message.setSubject("Ecom.com Order Confirmation");

	            message.setText(
	                    "Hello " + user.getName() + ",\n\n" +
	                    "Your order has been successfully confirmed.\n\n" +
	                    "Order ID: " + request.getOrderId() + "\n" +
	                    "Amount: ₹" + request.getAmount() + "\n" +
	                    "Payment Method: " + request.getPaymentMethod() + "\n" +
	                    "Payment Status: " + request.getPaymentStatus() + "\n\n" +
	                    "Thank you for shopping with Ecom.com."
	            );
	        }

	        // Email send
	        mailSender.send(message);


	        // ==============================
	        // SAVE NOTIFICATION
	        // ==============================

	        Notification notification = new Notification();

	        notification.setUserId(user.getUid());
	        notification.setOrderId(request.getOrderId());

	        // IMPORTANT: request se type lo
	        notification.setNotificationType(
	                request.getNotificationType()
	        );

	        notification.setChannel(NotificationChannel.EMAIL);

	        notification.setRecipient(user.getEmail());
	        notification.setRecipientName(user.getName());

	        notification.setSubject(message.getSubject());

	        notification.setMessage(message.getText());

	        notification.setStatus(NotificationStatus.SENT);

	        notification.setProvider("GMAIL");

	        notification.setRetryCount(0);
	        notification.setMaxRetryCount(3);

	        notification.setCreatedAt(LocalDateTime.now());
	        notification.setSentAt(LocalDateTime.now());

	        notificationRepository.save(notification);
	    }
	    
	    
}
