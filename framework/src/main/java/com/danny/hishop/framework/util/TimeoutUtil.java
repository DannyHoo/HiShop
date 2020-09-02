package com.danny.hishop.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java实现超时机制 https://www.jianshu.com/p/c2b8ad09315d
 *
 * @date 2020/4/20上午10:27
 */
public class TimeoutUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeoutUtil.class);

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
        private AtomicInteger tag = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread=new Thread(r);
            thread.setName("TimeOutControl-"+tag.getAndIncrement());
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static <R> R process(Callable<R> task, long timeout) throws TimeoutException {
        if (task == null) {
            return null;
        }
        Future<R> future = executor.submit(task);
        try {
            R result = future.get(timeout, TimeUnit.SECONDS);
            return result;
        } catch (InterruptedException e) {
            logger.error("Interrupt Exception", e);
        } catch (ExecutionException e) {
            logger.error("Task execute exception", e);
        } catch (TimeoutException e) {
            logger.warn("Process Timeout");
            if (future != null && !future.isCancelled()) {
                future.cancel(true);
            }
            throw e;
        }
        return null;
    }

}
