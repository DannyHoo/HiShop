package com.danny.hishop.gateway;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayProjectApplicationTests {

	@Test
	void contextLoads() {
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
