package com.danny.hishop.framework.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author huyuyang
 * @date 2019/11/6下午2:58
 */
public class ThreadMdcUtil {
    /**
     * 日志跟踪标识
     */
    public static final String LOG_TRACE_ID = "TRACE_ID";

    public static void setTraceIdIfAbsent() {
        if (MDC.get(LOG_TRACE_ID) == null) {
            MDC.put(LOG_TRACE_ID, UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }

    /**
     * 移除log trace id
     */
    public static void removeLogTraceId() {
        MDC.remove(LOG_TRACE_ID);
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
