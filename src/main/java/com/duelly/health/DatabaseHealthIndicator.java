package com.duelly.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Example logic
        boolean dbUp = false; // Replace with actual DB check

        if (dbUp) {
            return Health.up().withDetail("dbStatus", "Available").build();
        }
        return Health.down().withDetail("dbStatus", "Unavailable").build();
    }
}