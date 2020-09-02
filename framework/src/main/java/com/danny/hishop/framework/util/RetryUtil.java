package com.danny.hishop.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java 中实现方法重试的一种机制 https://www.cnblogs.com/HarrisonHao/p/7874902.html
 *
 * @date 2020/4/20上午10:20
 */
public class RetryUtil {
    private static Logger logger = LoggerFactory.getLogger(RetryUtil.class);

    private static ThreadLocal<Integer> retryTimesInThread = new ThreadLocal<>();

    private static RetryThreadLocal<Integer> retriedTimesInThread = new RetryThreadLocal<>(0);

    public static void main(String[] args) throws InterruptedException {
        String result = getDataWrapper();
    }

    private static String getDataWrapper() throws InterruptedException {
        String result = getData();
        if (true) {
            RetryUtil.setRetryTimes(3).retry();
        }
        return result;
    }

    private static String getData() throws InterruptedException {
        logger.info("getResult");
        return "success";
    }

    /**
     * 获取已经重试的次数
     *
     * @return
     */
    public static int retriedTimes() {
        return retriedTimesInThread.get();
    }

    /**
     * 设置当前方法重试次数
     *
     * @param retryTimes
     * @return
     */
    public static RetryUtil setRetryTimes(Integer retryTimes) {
        if (retryTimesInThread.get() == null) {
            retryTimesInThread.set(retryTimes);
        }
        return new RetryUtil();
    }

    /**
     * 重试当前方法
     * <p>按顺序传入调用者方法的所有参数</p>
     *
     * @param args
     * @return
     */
    public Object retry(Object... args) {
        try {
            Integer retryTimes = retryTimesInThread.get();
            if (retryTimes <= 0) {
                retryTimesInThread.remove();
                return null;
            }
            retryTimesInThread.set(--retryTimes);
            setRetriedTimesInThread(retriedTimesInThread);
            logger.info(Thread.currentThread().getStackTrace()[2].getMethodName() + "retry. times[{}]",retriedTimesInThread.get());

            String upperClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String upperMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            Class clazz = Class.forName(upperClassName);
            Object targetObject = clazz.newInstance();
            Method targetMethod = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(upperMethodName)) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod == null) {
                return null;
            }
            targetMethod.setAccessible(true);
            return targetMethod.invoke(targetObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setRetriedTimesInThread(RetryThreadLocal<Integer> retriedTimesInThread) {
        if (retriedTimesInThread.get()==null){
            retriedTimesInThread.set(1);
        }else{
            retriedTimesInThread.set(retriedTimesInThread.get()+1);
        }
    }


    /**
     * 重试当前方法
     * <p>按顺序传入调用者方法的所有参数</p>
     *
     * @param args
     * @return
     */
    /*public Object retry(Object... args) {
        try {
            Integer retryTimes = retryTimesInThread.get();
            if (retryTimes <= 0) {
                retryTimesInThread.remove();
                return null;
            }
            retryTimesInThread.set(--retryTimes);
            retriedTimesInThread.set(retriedTimesInThread.get() + 1);

            String upperClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String upperMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            Class clazz = Class.forName(upperClassName);
            Object targetObject = clazz.newInstance();
            Method targetMethod = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(upperMethodName)) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod == null) {
                return null;
            }
            targetMethod.setAccessible(true);
            return targetMethod.invoke(targetObject, args);
        } catch (ClassNotFoundException e) {
            logger.error("retry exception", e);
        } catch (InvocationTargetException e) {
            logger.error("retry exception", e);

        } catch (InstantiationException e) {
            logger.error("retry exception", e);

        } catch (IllegalAccessException e) {
            logger.error("retry exception", e);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }*/

    static class RetryThreadLocal<D> extends ThreadLocal<D> {
        public RetryThreadLocal() {
            super();
        }

        public RetryThreadLocal(D data) {
            this.set(data);
        }
    }
}
