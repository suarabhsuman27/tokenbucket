package com.api.ratelimiter.tokenbucket.serviceImpl;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.api.ratelimiter.tokenbucket.constants.AppConstant;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;

@Component
public class RateLimiter {

	private Map<String, Bucket> userToBucketMap = new HashMap<String, Bucket>();
	
	public boolean validateApiThrottling(String key) {
		
		Bucket bucket = resolveBucket(key);
		boolean throttle = true;
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if(!probe.isConsumed()) {
        	throttle = false;
        }
        return throttle;
	}
			
	private Bucket resolveBucket(String key) {
		Bucket bucket = userToBucketMap.computeIfAbsent(key, this::refillTokenInBucket);
		return bucket;
    }
	
    private Bucket refillTokenInBucket(String key) {
    	Refill refill = Refill.intervally(AppConstant.BUCKET_LIMIT, Duration.ofMinutes(AppConstant.BUCKET_REFILL_TIME_IN_MINUTES));
        Bandwidth limit = Bandwidth.classic(5, refill);
        Bucket bucket = Bucket.builder()
        				.addLimit(limit)
        				.build();
        return bucket;
    }
    
}
