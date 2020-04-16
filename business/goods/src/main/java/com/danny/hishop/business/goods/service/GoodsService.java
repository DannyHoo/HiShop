package com.danny.hishop.business.goods.service;


import com.danny.hishop.business.goods.model.dto.GoodsDTO;
import com.danny.hishop.business.goods.model.param.GoodsParameter;
import com.danny.hishop.business.goods.model.param.ReduceStockParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: GoodsService
 * @Description:
 * @Created on 2018-12-19 14:09:10
 */
public interface GoodsService {

    ServiceResult<GoodsDTO> findByGoodsNo(GoodsParameter goodsParameter);

    ServiceResult<Boolean> saveGoods(GoodsParameter setBalance);

    ServiceResult<Boolean> updateGoods(GoodsParameter setBalance);

    ServiceResult<Boolean> reduceStock(ReduceStockParameter reduceStockParameter);
}
