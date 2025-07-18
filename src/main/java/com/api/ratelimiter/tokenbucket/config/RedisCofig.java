package com.api.ratelimiter.tokenbucket.config;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;

@Configuration
public class RedisCofig {

	
	@Bean
	public Config config() {
		Config config = new Config();
		
		config.useSingleServer().setAddress("redis://localhost:6379");
		return config;
	}
	
	
	@Bean
	public CacheManager cacheManager(Config config) {
		CacheManager cache = Caching.getCachingProvider().getCacheManager();
		cache.createCache("cache", RedissonConfiguration.fromConfig(config));
		return cache;
	}
	
	
	@Bean
	public ProxyManager<String> proxyManager(CacheManager cacheManager){
		return new JCacheProxyManager<>(cacheManager.getCache("cache"));
	}
	
}
