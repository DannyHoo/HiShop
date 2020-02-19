package com.danny.hishop.business.aggregation.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.feign.goods.GoodsService;
import com.danny.hishop.business.aggregation.feign.user.UserService;
import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/25下午2:04
 */
public class GoodsServiceTest extends AggregationApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void getByGoodsNoTest() {
        Response<GoodsDTO> result=goodsService.getByGoodsNo("G20180614160305682062");
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void updateGoodsTest() {
        Response<Boolean>  result=goodsService.updateGoods(getGoodsParameter());
        System.out.println(JSONObject.toJSONString(result));
    }

    private GoodsParameter getGoodsParameter() {
        return new GoodsParameter()
                .setGoodsNo("G20180614160305682062")
                .setBalance(9);
    }

}
