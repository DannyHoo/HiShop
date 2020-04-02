package com.danny.hishop.business.aggregation.common;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/4/1下午8:47
 */
@Slf4j
public class CommonTest extends AggregationApplicationTests {

    @Value("${custom.dynamicPropertyTest:这是默认值}")
    private String dynamicProperty;

    @Test
    public void dynamicRefreshConfigTest(){
        while (true){
            try {
                Thread.sleep(100);
                log.info("dynamicProperty:{}",dynamicProperty);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
