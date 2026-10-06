package com.deepak.notification.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deepak.notification.DTO.UserResponse;
import com.deepak.notificationService.config.FeignConfig;

@FeignClient(name="MACY-USER-SERVICE" ,url="http://localhost:8082",configuration = FeignConfig.class)
public interface userClient {

	
	// get registered user details by  uid
	@GetMapping("/auth/getuser/{uid}")
	public UserResponse getUser(@PathVariable("uid") Long uid);
	
	
	
}
