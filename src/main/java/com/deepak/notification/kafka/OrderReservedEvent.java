package com.deepak.notification.kafka;

import lombok.Data;

@Data
public class OrderReservedEvent {
	private Long orderId;

    private String status;
    
}
