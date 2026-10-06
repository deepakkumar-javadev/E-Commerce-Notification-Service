package com.deepak.notification.DTO;

import com.deepak.notification.entity.NotificationType;

import lombok.Data;

@Data
public class NotificationRequestDto {

	private Long orderId;
    private Long userId;
    private String email;
    private String phoneNumber;
    private Double amount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private NotificationType notificationType;
}
