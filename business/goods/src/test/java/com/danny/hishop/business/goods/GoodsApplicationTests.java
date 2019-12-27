package com.danny.hishop.business.goods;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/26下午5:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
public class GoodsApplicationTests {
    protected static void printResult(Object result){
        System.out.println(JSON.toJSONString(result));
    }

}
