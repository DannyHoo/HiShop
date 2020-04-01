package com.danny.hishop.business.order.service.impl;


import com.danny.hishop.business.order.dao.OrderDetailDAO;
import com.danny.hishop.business.order.domain.OrderDetailDO;
import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.business.order.model.param.OrderDetailListParameter;
import com.danny.hishop.business.order.service.OrderDetailService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailServiceImpl
 * @Description:
 * @Created on 2019-02-26 16:40:10
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter) {
        List<OrderDetailDO> orderDetailDOList= BeanUtil.convertList(orderDetailListParameter.getOrderDetailDTOList(),OrderDetailDO.class);
        int result=orderDetailDAO.insertOrderDetailDOBatch(orderDetailDOList);
        if (result==orderDetailDOList.size()){
            return new ServiceResult<>(ResultStatusEnum.SUCCESS,BeanUtil.convertList(orderDetailDOList,OrderDetailDTO.class));
        }
        return new ServiceResult<>(ResultStatusEnum.FAILURE);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class,propagation= Propagation.REQUIRES_NEW)
    public OrderDetailDO saveOrderDetail(OrderDetailDO orderDetailDO) {
        orderDetailDAO.insertOrderDetailDO(orderDetailDO);
        return orderDetailDO;
    }

}
