package com.danny.hishop.business.order.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class DemoTask extends AbstractTask {

    private final static String LOCK_KEY = "DemoTask";

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 每10分钟触发任务
     */
    //@Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        super.start(0L, 5 * 60, TimeUnit.SECONDS);
    }


    @Override
    protected void execute() {
        log.info("DemoTask execute");
    }

    @Override
    protected String getLockKey() {
        return LOCK_KEY;
    }

}
