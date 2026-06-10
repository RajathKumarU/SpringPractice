package com.myspring.practice.SpringLearning.intw.ratelimit;

public class RateLimiterExecute {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5, 2);

        for (int i = 0; i < 10; i++) {
            boolean allowed = limiter.allowRequest();

            if (i == 7) {
                Thread.sleep(10);
            }
        }
    }
}
