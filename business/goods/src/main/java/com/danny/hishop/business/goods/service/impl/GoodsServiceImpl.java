package com.danny.hishop.business.goods.service.impl;

import com.danny.hishop.business.goods.dao.GoodsDAO;
import com.danny.hishop.business.goods.domain.GoodsDO;
import com.danny.hishop.business.goods.model.dto.GoodsDTO;
import com.danny.hishop.business.goods.model.param.GoodsParameter;
import com.danny.hishop.business.goods.service.GoodsService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Danny
 * @Title: GoodsServiceImpl
 * @Description:
 * @Created on 2018-12-19 14:09:20
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public ServiceResult<GoodsDTO> findByGoodsNo(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=goodsDAO.findByGoodsNo(goodsParameter.getGoodsNo());
        GoodsDTO goodsDTO= BeanUtil.convertIgnoreNullProperty(goodsDO,GoodsDTO.class);
        if (goodsDTO!=null){
            return new ServiceResult<GoodsDTO>(ResultStatusEnum.SUCCESS,goodsDTO);
        }
        return new ServiceResult<GoodsDTO>(ResultStatusEnum.GOODS_NOT_EXIST);
    }

    @Override
    public ServiceResult<Boolean> saveGoods(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=BeanUtil.convertIgnoreNullProperty(goodsParameter,GoodsDO.class);
        int count=goodsDAO.saveGoods(goodsDO);
        if (count<=0){
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,Boolean.TRUE);
    }

    @Override
    public ServiceResult<Boolean> updateGoods(GoodsParameter goodsParameter) {
        GoodsDO goodsDO=BeanUtil.convertIgnoreNullProperty(goodsParameter,GoodsDO.class);
        int count=goodsDAO.update(goodsDO);
        if (count<=0){
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,Boolean.TRUE);
    }
}
