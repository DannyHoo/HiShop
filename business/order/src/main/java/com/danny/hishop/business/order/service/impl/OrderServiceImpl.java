package com.danny.hishop.business.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.business.order.dao.OrderDAO;
import com.danny.hishop.business.order.domain.OrderDO;
import com.danny.hishop.business.order.feign.goods.GoodsService;
import com.danny.hishop.business.order.feign.goods.dto.GoodsDTO;
import com.danny.hishop.business.order.feign.user.UserService;
import com.danny.hishop.business.order.feign.user.dto.AddressDTO;
import com.danny.hishop.business.order.feign.user.dto.UserDTO;
import com.danny.hishop.business.order.model.dto.OrderDTO;
import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.business.order.model.param.CreateOrderParameter;
import com.danny.hishop.business.order.model.param.OrderDetailListParameter;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import com.danny.hishop.framework.util.ListUtil;
import com.danny.hishop.framework.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Danny
 * @Title: OrderServiceImpl
 * @Description:
 * @Created on 2019-01-09 23:10:26
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter) {
        OrderDO orderDO = BeanUtil.convertIgnoreNullProperty(orderParameter, OrderDO.class);
        int result = orderDAO.insertOrderDO(orderDO);
        if (result == 1 && orderDO.getId() != null) {
            OrderDTO orderDTO = BeanUtil.convertIgnoreNullProperty(orderDO, OrderDTO.class);
            return new ServiceResult<OrderDTO>(ResultStatusEnum.SUCCESS, orderDTO);
        }
        return new ServiceResult<OrderDTO>(ResultStatusEnum.FAILURE);

    }

    @Override
    public ServiceResult createOrder(CreateOrderParameter createOrderParameter) {
        ServiceResult serviceResult = new ServiceResult();

        try {
            //【参数校验】
            // 查询收货人信息
            AddressDTO addressDTO;
            Response<List<AddressDTO>> addressDTOResponse = userService.getAddressByUserName(createOrderParameter.getUserName());
            if (addressDTOResponse.isFail() || ListUtil.isEmpty(addressDTOResponse.getData())) {
                return new ServiceResult<>(ResultStatusEnum.USER_ADDRESS_NOT_EXIST);
            }
            addressDTO = addressDTOResponse.getData().get(0);
            log.info("查询收货人信息完成 addressDTO[{}]", JSON.toJSONString(addressDTO));

            //加锁

            //本地生成订单

            //扣减库存

            //锁定优惠券

        } catch (Exception e) {

        } finally {
            //解锁

            return serviceResult;
        }

    }

    private void initOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList, String orderNo) {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            orderDetailDTO.setTotalPrice(BigDecimal.ZERO)
                    .setActualPrice(BigDecimal.ZERO)
                    .setOrderNo(orderNo);
        }
    }
}
