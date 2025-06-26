package com.api.ratelimiter.tokenbucket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.api.ratelimiter.tokenbucket.interceptor.RateLimiterInterceptor;

@Configuration
public class RateLimiterConfig implements WebMvcConfigurer {
	
	@Bean
	public RateLimiterInterceptor requestInterceptor() {
		return new RateLimiterInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor()).addPathPatterns("/api/employee/**");
	}
}
