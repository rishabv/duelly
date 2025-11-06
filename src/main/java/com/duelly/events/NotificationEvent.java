package com.duelly.events;

import com.duelly.enums.EventType;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record NotificationEvent(
        UUID eventId,
        EventType eventType,
        Instant occurredAt,
        Long actorUserId,
        List<Long> targetUserIds,
        String aggregateType,
        String aggregateId,
        String templateKey,
        Map<String, Object> templateData,
        String traceId
) {}
