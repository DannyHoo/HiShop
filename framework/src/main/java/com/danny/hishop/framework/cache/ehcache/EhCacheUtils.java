package com.danny.hishop.framework.cache.ehcache;

import com.danny.hishop.framework.cache.ICache;

import java.util.Date;

/**
 * @author huyuyang
 * @date 2020/2/13下午5:27
 */
public class EhCacheUtils  implements ICache {
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
