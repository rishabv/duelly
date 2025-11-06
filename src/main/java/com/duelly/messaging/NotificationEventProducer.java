package com.duelly.messaging;

import com.duelly.events.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    @Value("${app.kafka.topics.notifications}")
    private String notificationTopic;

    public void publish(NotificationEvent event) {
        var msg = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, notificationTopic)
                .setHeader("traceId", event.traceId())
                .setHeader("eventId", String.valueOf(event.eventId()))
                .build();
        System.out.println(msg.getHeaders());
        kafkaTemplate.send(msg);
    }
}
