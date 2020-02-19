package com.danny.hishop.management;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementApplicationTests {

    @Test
    public void contextLoads() {
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
