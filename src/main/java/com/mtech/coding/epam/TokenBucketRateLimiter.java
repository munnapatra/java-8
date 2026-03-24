package com.mtech.coding.epam;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter {

    private final long capacity;
    private final long refillRatePerSecond;

    private AtomicLong availableTokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.availableTokens = new AtomicLong(capacity);
        this.lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refillTokens();
        if (availableTokens.get() > 0) {
            availableTokens.decrementAndGet();
            return true;
        }

        return false;
    }

    private void refillTokens() {
        long now = System.nanoTime();
        long elapsedSeconds = (now - lastRefillTimestamp) / 1_000_000_000;

        if (elapsedSeconds > 0) {

            long tokensToAdd = elapsedSeconds * refillRatePerSecond;
            long newTokenCount = Math.min(capacity,
                    availableTokens.get() + tokensToAdd);

            availableTokens.set(newTokenCount);
            lastRefillTimestamp = now;
        }
    }
}