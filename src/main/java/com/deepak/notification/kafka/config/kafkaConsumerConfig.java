package com.deepak.notification.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.deepak.notification.kafka.NotificationEventResponse;
import com.deepak.notification.kafka.OrderDeliveredEvent;
import com.deepak.notification.kafka.OrderReservedEvent;

@Configuration
public class kafkaConsumerConfig {


    private Map<String, Object> consumerProperties() {

        Map<String, Object> props = new HashMap<>();

        props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092"
        );

        props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            "notification-service"
        );

        props.put(
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
            "earliest"
        );

        props.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class
        );

        return props;
    }

    // ===============================
    // inventory.updated
    // ===============================

    @Bean
    public ConsumerFactory<String, NotificationEventResponse>
    notificationEventConsumerFactory() {

        JsonDeserializer<NotificationEventResponse> deserializer =
                new JsonDeserializer<>(NotificationEventResponse.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(
                consumerProperties(),
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationEventResponse>
    notificationEventKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, NotificationEventResponse> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(notificationEventConsumerFactory());

        return factory;
    }


    // ===============================
    // inventory.reserved
    // ===============================

    @Bean
    public ConsumerFactory<String, OrderReservedEvent>
    orderReservedConsumerFactory() {

        JsonDeserializer<OrderReservedEvent> deserializer =
                new JsonDeserializer<>(OrderReservedEvent.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(
                consumerProperties(),
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderReservedEvent>
    orderReservedKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrderReservedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(orderReservedConsumerFactory());

        return factory;
    }


    // ===============================
    // order.delivered
    // ===============================

    @Bean
    public ConsumerFactory<String, OrderDeliveredEvent>
    orderDeliveredConsumerFactory() {

        JsonDeserializer<OrderDeliveredEvent> deserializer =
                new JsonDeserializer<>(OrderDeliveredEvent.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(
                consumerProperties(),
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDeliveredEvent>
    orderDeliveredKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrderDeliveredEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(orderDeliveredConsumerFactory());

        return factory;
    }
	
}
