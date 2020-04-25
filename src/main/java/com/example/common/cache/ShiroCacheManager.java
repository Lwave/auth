package com.example.common.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroCacheManager<K,V> implements CacheManager {

    private RedisTemplate<K,V> redisTemplate;

    public ShiroCacheManager(RedisTemplate<K,V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new ShiroCache(redisTemplate,cacheName);
    }
}
