package com.mtech.coding.epam;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {

    private final int maxRequests;
    private final long windowSizeMillis;

    private long windowStart;
    private AtomicInteger requestCount = new AtomicInteger(0);

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - windowStart >= windowSizeMillis) {
            windowStart = currentTime;
            requestCount.set(0);
        }

        if (requestCount.incrementAndGet() <= maxRequests) {
            return true;
        }

        return false;
    }
}