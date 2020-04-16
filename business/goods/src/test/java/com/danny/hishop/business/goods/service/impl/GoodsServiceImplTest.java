package com.danny.hishop.business.goods.service.impl;

import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.model.param.GoodsParameter;
import com.danny.hishop.business.goods.model.param.ReduceStockParameter;
import com.danny.hishop.business.goods.service.GoodsService;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/4/16下午1:57
 */
public class GoodsServiceImplTest extends GoodsApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void selectForUpdateTest1(){
        ReduceStockParameter reduceStockParameter=new ReduceStockParameter()
                .setGoodsId(1L)
                .setGoodsNo("G20180614160305682062")
                .setNum(1);
        ServiceResult<Boolean> result= goodsService.reduceStock(reduceStockParameter);
        printResult(result);
    }

    @Test
    public void selectForUpdateTest2(){
        ReduceStockParameter reduceStockParameter=new ReduceStockParameter()
                .setGoodsId(2L)
                .setGoodsNo("G201806141603056820621")
                .setNum(1);
        ServiceResult<Boolean> result= goodsService.reduceStock(reduceStockParameter);
        printResult(result);
    }

    /*
    * select for update 如果一个事务处理时间太长，其他事务可能会出现等待锁时间超时报错
    * org.springframework.dao.CannotAcquireLockException:
    ### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
    ### The error may exist in com/danny/hishop/business/goods/dao/GoodsDAO.java (best guess)
    ### The error may involve com.danny.hishop.business.goods.dao.GoodsDAO.findByGoodsNo-Inline
    ### The error occurred while setting parameters
    ### SQL: select * from t_goods where goodsNo=? for update
    ### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
    ; Lock wait timeout exceeded; try restarting transaction; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
    * */
}
