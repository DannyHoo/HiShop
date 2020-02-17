package com.danny.hishop.business.aggregation.common;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.framework.util.DateUtils;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import com.danny.hishop.framework.util.test.Executor;
import com.danny.hishop.framework.util.test.ExecutorInterface;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/2/17下午2:31
 */
public class SnowflakeTest extends AggregationApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Test
    public void snowflakeTest(){
        int threadNum=100;
        long snowflakeId=snowflake.genId();
        System.out.println(threadNum+"个线程开始打印，开始时间："+ DateUtils.getFullDateFormat(DateUtils.getNowDate()));

        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() {
                long snowflakeId=snowflake.genId();
                System.out.println(Thread.currentThread().getName()+":"+snowflakeId);
                System.out.println(Thread.currentThread().getName()+":"+Long.toBinaryString(snowflakeId));
            }
        });
        executor.start(threadNum);

        System.out.println(threadNum+"个线程打印完成，结束时间："+ DateUtils.getFullDateFormat(DateUtils.getNowDate()));

        //678978287919939597
        //1001011011 0000110111 0010011111 0011011010 1011110000 0000001101
        //9285347008512
        //10000111000111101001110011110110000000000001
        //100101101100001110001001001001001110010000000000000000111111
        //678979844855300159
    }
}
