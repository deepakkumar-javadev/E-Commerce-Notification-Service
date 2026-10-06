package com.deepak.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.notification.DTO.NotificationRequestDto;

import com.deepak.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService service;

	@PostMapping("/send")
	public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDto request) {

		service.sendOrderConfirmation(request);

		return ResponseEntity.ok("Notification sent successfully");
	}

	@PostMapping("/sendmessage")
	public ResponseEntity<String> sendNotificationMessage(@RequestBody NotificationRequestDto request) {

		service.sendOrderConfirmationMessage(request);

		return ResponseEntity.ok("Notification sent successfully");
	}

}
