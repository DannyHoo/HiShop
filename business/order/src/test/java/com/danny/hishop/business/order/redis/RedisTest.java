package com.danny.hishop.business.order.redis;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.business.order.OrderApplicationTests;
import com.danny.hishop.business.order.domain.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @date 2020/3/27下午6:11
 */
@Slf4j

public class RedisTest extends OrderApplicationTests {

    @Autowired
    protected RedissonClient redissonClient;

    @Test
    public void test(){
        RList<OrderDO> orderDORList = redissonClient.getList("OrderCache");
        if (orderDORList.isExists() && !orderDORList.isEmpty()) {
            log.info("删除原始缓存 cache[{}]", JSON.toJSONString(orderDORList.readAll()));
            orderDORList.delete();
        }
        System.out.println(orderDORList.size());
        orderDORList.delete();
        System.out.println();
    }


}
