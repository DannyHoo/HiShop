package com.danny.hishop.business.aggregation;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.framework.util.ThreadMdcUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.annotation.EnableSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huyuyang
 * @date 2019/11/25下午2:34
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AggregationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AggregationApplicationTests {

    @Before
    public void before() {
        log.info("setTraceIdIfAbsent");
        ThreadMdcUtil.setTraceIdIfAbsent();
    }

    @After
    public void after() {
        log.info("removeLogTraceId");
        ThreadMdcUtil.removeLogTraceId();
    }

    protected static void printResult(Object... result) {
        for (Object object : result) {
            if (object == null) {
                System.out.println("null");
            } else if (object instanceof String) {
                System.out.println(object);
            } else {
                System.out.println(JSON.toJSONString(object));
            }
        }
    }

}
