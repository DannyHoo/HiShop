package com.danny.hishop.framework.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huyuyang
 * @date 2020/2/13下午5:22
 */
public interface ICache {

    public <T> T getObject(String key, Class<T> clazz);

    public void setObject(String key, Object value);

    public void setObject(String key, Object value, Date expireDate);

}
