package com.danny.hishop.business.aggregation.common;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.framework.cache.redis.RedisCacheUtils;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/2/13下午3:03
 */
public class RedisCacheUtilsTest extends AggregationApplicationTests {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private Snowflake snowflake;

    @Test
    public void redsiTest(){
        Person person =new Person(20,"朱小明",new Date());

        redisCacheUtils.setObject("a",person);
        Person a= redisCacheUtils.getObject("a",Person.class);

        Person b;
        RBucket<Person> bucket=redissonClient.getBucket("b");
        //if (!bucket.isExists()){
        if (true){
            bucket.set(person);
            bucket.expire(24, TimeUnit.HOURS);
        }
        bucket=redissonClient.getBucket("b");
        b=bucket.get();

        redisCacheUtils.setString("c","c");
        String c=redisCacheUtils.getString("c");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    @Data
    @Accessors
    static class Person implements Serializable {
        public Person() {
        }

        public Person(int age, String name, Date birthday) {
            this.age = age;
            this.name = name;
            this.birthday = birthday;
        }

        private int age;
        private String name;
        private Date birthday;
    }


}
