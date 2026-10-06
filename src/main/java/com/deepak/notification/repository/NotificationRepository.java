package com.deepak.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepak.notification.entity.Notification;

@Repository	
public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
