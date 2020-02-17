package com.danny.hishop.framework.cache.redis;

import com.danny.hishop.framework.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate默认采用JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。
 * StringRedisTemplate默认采用String的序列化策略，保存的key和value都是采用此策略序列化保存的。
 * Redisson 默认采用Jackson序列化（org.redisson.codec.JsonJacksonCodec）
 */
@Component
public class RedisCacheUtils implements ICache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    /******************************************************/
    /****************** 操作一般key-value ******************/
    /******************************************************/

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setObject(String key, Object value, Date expireDate) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public  Double setDouble(String key, Double value, Date expireDate) {
        redisTemplate.opsForValue().set(key, value);
        if (expireDate != null) {
            redisTemplate.expire(key, expireDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        return value;
    }


    /******************************************************/
    /********************* 操作double *********************/
    /******************************************************/

    public  Double getDouble(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value instanceof Long) {
            return (Double) value;
        }
        return value == null ? 0 : Double.valueOf(value.toString());
    }

    public  Double incDouble(String key, Double increament, Date expireDate) {
        double value = redisTemplate.opsForValue().increment(key, increament);
        if (expireDate != null) {
            redisTemplate.expire(key, expireDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        return value;
    }


    /******************************************************/
    /********************** 操作long **********************/
    /******************************************************/

    /**
     * 获取Long值
     *
     * @param key          缓存key
     * @param defaultValue 默认值
     * @return Long值
     */
    public  Long getLong(String key, Long defaultValue) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value instanceof Long) {
            return (Long) value;
        }
        return value == null ? defaultValue : Long.valueOf(value.toString());
    }

    /**
     * Long自增
     *
     * @param key        缓存key
     * @param expireDate 过期时间，可以为空，只在key创建时设置
     * @param increment  增量，不能小于0，为空则+1
     * @return 增加后的值
     */
    public  Long inc(String key, Date expireDate, Long increment) {
        increment = increment == null ? 1L : increment;
        if (increment < 0) {
            return null;
        }
        long num = redisTemplate.opsForValue().increment(key, increment);
        if (num == increment && expireDate != null) {
            redisTemplate.expire(key, expireDate.getTime() - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS);
        }
        return num;
    }

    /**
     * Long自减
     *
     * @param key       缓存key
     * @param decrement 减量，不能小于0，为空则-1
     * @return 增加后的值
     */
    public  Long dec(String key, Long decrement) {
        decrement = decrement == null ? 1L : decrement;
        if (decrement < 0) {
            return null;
        }
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return redisTemplate.opsForValue().decrement(key, decrement);
    }


    /******************************************************/
    /********************** 操作int ***********************/
    /******************************************************/

    /**
     * 获取Integer值
     *
     * @param key          缓存key
     * @param defaultValue 默认值
     * @return Long值
     */
    public  Integer getInteger(String key, Integer defaultValue) {
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? defaultValue : Integer.valueOf(value.toString());
    }


    /******************************************************/
    /*********************** 操作set ***********************/
    /******************************************************/

    /**
     * 获取set长度
     *
     * @param key 键
     * @return
     */
    public  Long getSetSize(String key) {
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForSet().size(key);
        }
        return 0L;
    }

    /**
     * 添加set
     * @param key
     * @param expireDate
     * @param objects
     * @return
     */
    public  Long addSet(String key, Date expireDate, Object... objects) {
        Long result = redisTemplate.opsForSet().add(key, objects);
        redisTemplate.expire(key, expireDate.getTime() - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
        return result;
    }





    /**
     * expire为过期时间，秒为单位
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(key);
    }
}