package com.example.common.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public  class CacheConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Autowired
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    /**
     * Redis 和 SpringCache 整合的缓存管理器
     * @return
     */
    @Bean(name = "springCacheManager")
    public CacheManager cacheManager(){
        // 创建一个Redis缓存的默认配置
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig();
        // 配置序列化的规则
        conf = conf.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .entryTtl(Duration.ofMinutes(-1));//设置缓存的默认过期时间
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(conf)
                .build();
        return cacheManager;
    }
}


