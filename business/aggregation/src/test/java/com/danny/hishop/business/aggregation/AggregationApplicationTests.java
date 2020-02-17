package com.danny.hishop.business.aggregation;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.framework.util.snowflake.autoconfigure.annotation.EnableSnowflake;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/25下午2:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AggregationApplication.class)
public class AggregationApplicationTests {

    protected static void printResult(Object result){
        System.out.println(JSON.toJSONString(result));
    }

}
