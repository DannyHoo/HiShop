package com.danny.hishop.framework.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类
 */
public class RedissonLockUtils {

    private static Locker locker;

    /**
     * 设置工具类使用的locker
     */
    public static void setLocker(Locker locker) {
        RedissonLockUtils.locker = locker;
    }

    /**
     * 获取锁
     */
    public static void lock(String lockKey) {
        locker.lock(lockKey);
    }

    /**
     * 释放锁
     */
    public static void unlock(String lockKey) {
        locker.unlock(lockKey);
    }

    /**
     * 获取锁，超时释放
     */
    public static void lock(String lockKey, int timeout) {
        locker.lock(lockKey, timeout);
    }

    /**
     * 获取锁，超时释放，指定时间单位
     */
    public static void lock(String lockKey, TimeUnit unit, int timeout) {
        locker.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁，获取到立即返回true,获取失败立即返回false
     */
    public static boolean tryLock(String lockKey) {
        return locker.tryLock(lockKey);
    }

    /**
     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
     */
    public static boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit)
            throws InterruptedException {
        return locker.tryLock(lockKey, waitTime, leaseTime, unit);
    }

    /**
     * 同步执行代码指定过程
     *
     * @param lockKey    锁key
     * @param waitTime   尝试次数
     * @param leaseTime  超时时间
     * @param unit       时间单位
     * @param lockedCall 锁定后的操作
     * @param unlockCall 锁定失败的操作
     * @param <T>        返回值类型
     * @return 执行过程的返回值
     */
    public static <T> T tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit,
                                Callable<T> lockedCall, Callable<T> unlockCall) {
        try {
            if (tryLock(lockKey, waitTime, leaseTime, unit)) {
                return lockedCall.call();
            } else {
                return unlockCall.call();
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (isLocked(lockKey)) {
                unlock(lockKey);
            }
        }
    }

    /**
     * 锁释放被任意一个线程持有
     */
    public static boolean isLocked(String lockKey) {
        return locker.isLocked(lockKey);
    }
}
