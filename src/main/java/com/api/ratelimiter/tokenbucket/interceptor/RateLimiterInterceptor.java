package com.api.ratelimiter.tokenbucket.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.api.ratelimiter.tokenbucket.constants.AppConstant;
import com.api.ratelimiter.tokenbucket.serviceImpl.RateLimiter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
	
	@Autowired
	RateLimiter rateLimiter;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("In Pre-Handler");
		String key = request.getHeader("x-api-key");
        if (key == null || key.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: Key");
            return false;
        }
		if(Boolean.FALSE.equals(rateLimiter.validateApiThrottling(key))) {
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
              "You have exhausted your API Request Quota - "+AppConstant.BUCKET_LIMIT + "/Minute"); 
            return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null){
            //exception handle part
            System.out.println("An error occured.");
        }
        System.out.println("3 - after completion.");
	}
}
