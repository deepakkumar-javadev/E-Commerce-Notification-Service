package com.deepak.notification.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;


@Entity

@Table(name = "notifications", indexes = { 
		@Index(name = "idx_notification_user", columnList = "user_id"),
		@Index(name = "idx_notification_order", columnList = "order_id"),
		@Index(name = "idx_notification_status", columnList = "status"),
		@Index(name = "idx_notification_created", columnList = "created_at"),
		@Index(name = "idx_notification_event", columnList = "event_id") 
		})

@Data
public class Notification {

	// =========================================================
	// PRIMARY KEY
	// =========================================================

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// =========================================================
	// USER / ORDER INFORMATION
	// =========================================================

	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "order_id")
	private Long orderId;

	
	// =========================================================
	// NOTIFICATION INFORMATION
	// =========================================================

	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type", nullable = false)
	private NotificationType notificationType;

	@Enumerated(EnumType.STRING)
	@Column(name = "channel", nullable = false)
	private NotificationChannel channel;

	// =========================================================
	// RECIPIENT INFORMATION
	// =========================================================

	@Column(name = "recipient", nullable = false)
	private String recipient;

	private String recipientName;

	// =========================================================
	// MESSAGE INFORMATION
	// =========================================================

	private String subject;

	@Column(columnDefinition = "TEXT")
	private String message;

	private String templateName;

	private String templateVersion;

	// =========================================================
	// DELIVERY INFORMATION
	// =========================================================

	@Enumerated(EnumType.STRING)   //to consider enum as String 
	@Column(name = "status", nullable = false)
	private NotificationStatus status;

	private String provider;

	private String providerMessageId;

	@Column(columnDefinition = "TEXT")
	private String providerResponse;

	// =========================================================
	// RETRY INFORMATION
	// =========================================================

	private Integer retryCount;

	private Integer maxRetryCount;

	private LocalDateTime nextRetryAt;

	@Column(columnDefinition = "TEXT")
	private String failureReason;

	// =========================================================
	// TIMESTAMP INFORMATION
	// =========================================================

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private LocalDateTime sentAt;

	private LocalDateTime deliveredAt;

	private LocalDateTime failedAt;

	// =========================================================
	// EVENT / KAFKA INFORMATION
	// =========================================================

	@Column(name = "event_id")
	private String eventId;

	private String correlationId;

	private String sourceService;

	private String idempotencyKey;
}
