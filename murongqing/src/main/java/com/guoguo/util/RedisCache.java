package com.guoguo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, int minute){
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.SECONDS);
    }

    public void set(String key, String value){
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean del(String key){
        return stringRedisTemplate.delete(key);
    }
}
