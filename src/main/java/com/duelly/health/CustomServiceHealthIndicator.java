package com.duelly.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomServiceHealthIndicator implements HealthIndicator {
    @Override
    public Health health(){
        boolean ok = quickCheck();
        if (ok) {
            return Health.up().withDetail("customService", "reachable").build();
        } else {
            return Health.down().withDetail("customService", "unreachable").build();
        }
    }

    private boolean quickCheck() {
        // do a cheap in-memory or cached check
        return true; // replace with real quick health check
    }
}
