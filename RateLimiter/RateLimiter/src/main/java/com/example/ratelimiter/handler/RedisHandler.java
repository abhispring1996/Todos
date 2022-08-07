package com.example.ratelimiter.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Component
public class RedisHandler {

    @Autowired
    StringRedisTemplate redisTemplate;

    private ValueOperations valueOperations;
    private HashOperations hashOperations;

    @PostConstruct
    public void init(){
        valueOperations = redisTemplate.opsForValue();
        hashOperations = redisTemplate.opsForHash();
    }

    public boolean setIfAbsent(String key,Object value,long ttl){
        return Boolean.TRUE.equals(valueOperations.setIfAbsent(key, value.toString(), Duration.ofSeconds(ttl)));
    }

    public void set(String key,Object value,long ttl){
        valueOperations.set(key, value.toString(), Duration.ofSeconds(ttl));
    }

    public String get(String key){
        Object noOfRequests = valueOperations.get(key);

        if(null!=noOfRequests){
           return  (String)noOfRequests;
        }
        return null;
    }

    public Long decrementKey(String key){
        return valueOperations.decrement(key);
    }
}
