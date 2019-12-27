package com.danny.hishop.business.goods.dao;

import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.domain.GoodsDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/26下午5:46
 */
public class GoodsDaoTest extends GoodsApplicationTests {

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    public void a() {
        GoodsDO goodsDO = goodsDAO.findByGoodsNo("G20180614160305682062");
        goodsDO.setBalance(100);
        int result = goodsDAO.update(goodsDO);
        System.out.println();
    }

    @Test
    public void b() {
        GoodsDO goodsDO = goodsDAO.findByGoodsNo("G20180614160305682062");
        if (goodsDO.getBalance()>0){
            goodsDO.setBalance(goodsDO.getBalance()-1);
        }
        int result = goodsDAO.update(goodsDO);
        printResult(result);
    }
}
