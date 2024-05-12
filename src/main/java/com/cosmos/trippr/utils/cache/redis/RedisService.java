package com.cosmos.trippr.utils.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  @Autowired
  public RedisService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void saveToRedis(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public Object getFromRedis(String key) {
    return redisTemplate.opsForValue().get(key);
  }
}
