package com.myspring.practice.SpringLearning.intw.ratelimit;

import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {
    private final long windowSize;
    private final long capacity;
    private final Queue<Long> requests;

    public RateLimiter(long size, long capacity) {
        this.windowSize = size;
        this.capacity = capacity;
        this.requests = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long winStart = now - windowSize;

        // check and remove older window requests
        clearOldRequests(winStart);

        // Allow based on request size
        if(requests.size() < capacity) {
            requests.add(now);
            System.out.println("Allowed at: " + now);
            return true;
        }

        // remove lock

        System.out.println("Limit exceeded, Please try again later at: " + now);
        return false;
    }

    private void clearOldRequests(long winStart) {
        while (!requests.isEmpty() && requests.peek() < winStart) {
            requests.poll();
        }
    }


}
