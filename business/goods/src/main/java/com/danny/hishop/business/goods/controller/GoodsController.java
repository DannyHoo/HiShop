package com.danny.hishop.business.goods.controller;

import com.danny.hishop.business.goods.model.param.GoodsParameter;
import com.danny.hishop.business.goods.service.GoodsService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:36
 */
@RestController
@RequestMapping("/goods/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/getByGoodsNo/{goodsNo}", method = RequestMethod.GET)
    public Response getByGoodsNo(@PathVariable String goodsNo) {
        ServiceResult result = goodsService.findByGoodsNo(new GoodsParameter().setGoodsNo(goodsNo));
        return Response.build(result);
    }


    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public Response updateGoods(@RequestBody GoodsParameter goodsParameter) throws Exception {
        ServiceResult result = goodsService.updateGoods(goodsParameter);
        return Response.build(result);
    }
}
