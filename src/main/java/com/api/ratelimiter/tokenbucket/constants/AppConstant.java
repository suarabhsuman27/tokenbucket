package com.api.ratelimiter.tokenbucket.constants;

public interface AppConstant {

	String SUCCESS_CODE = "200";
	
	String BAD_REQUEST_CODE = "400";
	
	String INTERNAL_SERVER_ERROR = "500";
	
	long BUCKET_LIMIT = 5;
	
	long BUCKET_REFILL_TIME_IN_MINUTES  = 1;
}
