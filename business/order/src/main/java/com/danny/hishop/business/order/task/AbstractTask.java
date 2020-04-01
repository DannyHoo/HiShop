package com.danny.hishop.business.order.task;

import com.danny.hishop.framework.lock.RedissonLockUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *
 * @date 2020/3/04下午2:45
 */
@Slf4j
public abstract class AbstractTask {

    /**
     * 开始执行定时任务
     */
    public void start(long waitLockTime, long leaseLockTime, TimeUnit timeUnit) {
        long startTime = 0L;
        boolean isHoldLock = false;
        try {
            isHoldLock = RedissonLockUtils.tryLock(getLockKey(), waitLockTime, leaseLockTime, timeUnit);
            if (isHoldLock) {
                log.info("定时任务获取到锁,开始执行 lockKey[{}]", getLockKey());
                startTime = System.currentTimeMillis();
                try {
                    execute();
                } catch (Exception e) {
                    log.warn("定时任务执行失败", e);
                }
            } else {
                log.info("定时任务获取锁超时,结束执行 lockKey[{}]", getLockKey());
            }
        } catch (Exception e) {
            log.warn("定时任务获取锁失败 lockKey[" + getLockKey() + "]", e);
        } finally {
            if (isHoldLock && RedissonLockUtils.isLocked(getLockKey())) {
                RedissonLockUtils.unlock(getLockKey());
                log.info("定时任务释放锁,执行结束 lockKey[{}] 耗时[{}]", getLockKey(), System.currentTimeMillis() - startTime);
            }
        }
    }

    /**
     * 定义分布式锁的key
     *
     * @return
     */
    protected abstract String getLockKey();

    /**
     * 定时任务内容
     */
    protected abstract void execute();

}
