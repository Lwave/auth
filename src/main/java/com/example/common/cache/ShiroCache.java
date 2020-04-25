package com.example.common.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义Shiro缓存，实现 shiro Cache 接口
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    private String cacheName;

    public ShiroCache(RedisTemplate<K, V> redisTemplate, String cacheName) {
        this.redisTemplate = redisTemplate;
        this.cacheName = cacheName;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("新增缓存：" + cacheName);
        redisTemplate.opsForHash().put((K) cacheName, k, v);
        return v;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("获取缓存：" + cacheName);
        return (V) redisTemplate.opsForHash().get((K) cacheName, k);
    }


    @Override
    public V remove(K k) throws CacheException {
        System.out.println("删除缓存：" + cacheName);
        V value = get(k);
        redisTemplate.opsForHash().delete((K) cacheName, k);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("清除所有用户缓存：" + cacheName);
        redisTemplate.delete((K) cacheName);
    }

    @Override
    public int size() {
        return redisTemplate.opsForHash().size((K) cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) redisTemplate.opsForHash().keys((K) cacheName);
    }

    @Override
    public Collection<V> values() {
        return (Collection<V>) redisTemplate.opsForHash().values((K) cacheName);
    }
}

//Hset
// key 固定值  k  value
