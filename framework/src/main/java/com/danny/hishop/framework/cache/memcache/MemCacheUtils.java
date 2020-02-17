package com.danny.hishop.framework.cache.memcache;

import com.danny.hishop.framework.cache.ICache;

import java.util.Date;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/2/13下午5:27
 */
public class MemCacheUtils implements ICache {
    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public void setObject(String key, Object value) {

    }

    @Override
    public void setObject(String key, Object value, Date expireDate) {

    }
}
