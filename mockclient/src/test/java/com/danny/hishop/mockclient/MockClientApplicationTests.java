package com.danny.hishop.mockclient;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huyuyang
 * @date 2019/11/25下午2:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockClientApplication.class)
public class MockClientApplicationTests {

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
