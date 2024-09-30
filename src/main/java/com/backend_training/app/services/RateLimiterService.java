package com.backend_training.app.services;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimiterService {

    private final ConcurrentMap<String, RateLimiter> userRateLimiters = new ConcurrentHashMap<>();
    private final RateLimiterRegistry rateLimiterRegistry;

    @Autowired
    public RateLimiterService(RateLimiterRegistry rateLimiterRegistry) {
        this.rateLimiterRegistry = rateLimiterRegistry;
    }

    public void createRateLimiter(String userId) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(5)
                .limitRefreshPeriod(Duration.ofSeconds(60))
                .build();
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(userId, config);
        userRateLimiters.put(userId, rateLimiter);
    }

    public boolean tryAcquire(String userId) {
        RateLimiter rateLimiter = userRateLimiters.get(userId);
        if (rateLimiter == null) {
            createRateLimiter(userId);
            rateLimiter = userRateLimiters.get(userId);
        }
        return rateLimiter.acquirePermission();
    }
}
