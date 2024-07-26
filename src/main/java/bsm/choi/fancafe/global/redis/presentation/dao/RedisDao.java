package bsm.choi.fancafe.global.redis.presentation.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

// 토큰 확인 및 저장 역할을 하는 DAO

@Component
@RequiredArgsConstructor
public class RedisDao {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setValues(String key, String data) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, data);
    }

    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, data, duration);
    }

    public Object getValues(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
