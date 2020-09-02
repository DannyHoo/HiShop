package com.danny.hishop.business.order.util;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.RetryUtil;
import com.danny.hishop.framework.util.TimeoutUtil;
import com.danny.hishop.framework.util.test.Executor;
import com.danny.hishop.framework.util.test.ExecutorInterface;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * @date 2020/4/20下午8:56
 */
@Slf4j
public class TimeoutRetryTest {

    private static long timeout=2;
    private static int retryTimes=3;

    public static void main(String[] args) {
        Executor executor=new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() {
                DataInfo dataInfo=getDataInfoWrapper();
                System.out.println(JSON.toJSONString(dataInfo));
            }
        });
        executor.start(5);
    }

    public static DataInfo getDataInfoWrapper() {
        try {
            Callable<DataInfo> callable = new Callable<DataInfo>() {
                @Override
                public DataInfo call() throws Exception {
                    return getDataInfo();
                }
            };
            return TimeoutUtil.process(callable, timeout);
        } catch (TimeoutException e) {
            log.info("invoke timeout retry");
            return (DataInfo) RetryUtil.setRetryTimes(retryTimes).retry();
        }
    }

    public static DataInfo getDataInfo() {
        log.info("getDataInfo");
        try {
            int sleepSeconds=RandomValueUtil.randomIntValue(1,6);
            log.info("执行耗时[{}]s",sleepSeconds);
            Thread.sleep(sleepSeconds*1000);
        } catch (InterruptedException e) {
            log.error("getDataInfo InterruptedException");
        }
        return new DataInfo().setId(1).setName("name");
    }

    @Data
    @Accessors(chain = true)
    public static class DataInfo {
        private Integer id;
        private String name;
    }
}
