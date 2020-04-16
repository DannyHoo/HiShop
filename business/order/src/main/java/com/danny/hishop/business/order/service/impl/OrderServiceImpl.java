package com.danny.hishop.business.order.service.impl;

import com.danny.hishop.business.order.dao.OrderDAO;
import com.danny.hishop.business.order.domain.OrderDO;
import com.danny.hishop.business.order.model.dto.OrderDTO;
import com.danny.hishop.business.order.model.param.CreateOrderParameter;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Danny
 * @Title: OrderServiceImpl
 * @Description:
 * @Created on 2019-01-09 23:10:26
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter) {
        OrderDO orderDO= BeanUtil.convertIgnoreNullProperty(orderParameter,OrderDO.class);
        int result=orderDAO.insertOrderDO(orderDO);
        if (result==1 && orderDO.getId()!=null){
            OrderDTO orderDTO= BeanUtil.convertIgnoreNullProperty(orderDO,OrderDTO.class);
            return new ServiceResult<OrderDTO>(ResultStatusEnum.SUCCESS,orderDTO);
        }
        return new ServiceResult<OrderDTO>(ResultStatusEnum.FAILURE);

    }

    @Override
    public ServiceResult createOrder(CreateOrderParameter createOrderParameter) {
        return null;
    }
}
