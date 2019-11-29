package com.danny.hishop.business.aggregation.service;


import com.danny.hishop.business.aggregation.feign.goods.GoodsService;
import com.danny.hishop.business.aggregation.feign.order.OrderService;
import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDTO;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderDetailListParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.business.aggregation.model.result.order.CreateOrderResult;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.business.aggregation.feign.user.UserService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.mq.MQProducer;
import com.danny.hishop.framework.util.BeanUtil;
import com.danny.hishop.framework.util.ListUtil;
import com.danny.hishop.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Danny
 * @Title: OrderBusinessServiceImpl
 * @Description:
 * @Created on 2018-12-21 16:01:41
 */
@Service("orderBusinessService")
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    private static final Lock lock = new ReentrantLock();

    @Override
    public ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter) {

        //已有数据准备
        UserDTO userDTO = createOrderParameter.getUserDTO();
        List<OrderDetailDTO> orderDetailDTOList = createOrderParameter.getOrderDetailDTOList();

        /* 查询收货人信息 */
        AddressDTO addressDTO;
        ServiceResult<List<AddressDTO>> addressDTOServiceResult = userService.getAddressByUserName(userDTO.getUserName());
        if (addressDTOServiceResult.isFail() || ListUtil.isEmpty(addressDTOServiceResult.getData())) {
            return new ServiceResult<>(ResultStatusEnum.USER_ADDRESS_NOT_EXIST);
        }
        addressDTO = addressDTOServiceResult.getData().get(0);

        /* 远程调用 更新商品库存 */
        String orderNo = "OD" + StringUtil.getRandomTimeStr();
        initOrderDetailDTOList(orderDetailDTOList, orderNo);

        //加锁开始
        lock.lock();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            //校验商品信息
            ServiceResult<GoodsDTO> goodsDTOServiceResult = goodsService.getByGoodsNo(orderDetailDTO.getGoodsNo());
            if (goodsDTOServiceResult.isFail() || goodsDTOServiceResult.getData() == null) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_NOT_EXIST);
            }
            //校验商品数量
            GoodsDTO goodsDTO = goodsDTOServiceResult.getData();
            if (goodsDTO.getBalance() < orderDetailDTO.getGoodsNum()) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_BALANCE_NOT_ENOUGH);
            }
            orderDetailDTO.setActualPrice(orderDetailDTO.getActualPrice().add(new BigDecimal(orderDetailDTO.getGoodsNum()).multiply(goodsDTO.getNowPrice())));
            orderDetailDTO.setTotalPrice(orderDetailDTO.getTotalPrice().add(new BigDecimal(orderDetailDTO.getGoodsNum()).multiply(goodsDTO.getOriginPrice())));
            //远程调用更新库存
            ServiceResult<Boolean> saveGoodsResult = goodsService.updateGoods(BeanUtil.convertIgnoreNullProperty(goodsDTO, GoodsParameter.class).setBalance(goodsDTO.getBalance() - orderDetailDTO.getGoodsNum()));
            if (saveGoodsResult.isFail() || !Boolean.TRUE.equals(saveGoodsResult.getData())) {
                return new ServiceResult<>(ResultStatusEnum.GOODS_BALANCE_UPDATE_FAILURE);
            }
        }
        //加锁结束
        lock.unlock();

        /* 计算价格 */
        BigDecimal totalPrice, cutDownPrice, freight, actualPrice;
        totalPrice = calcTotalPrice(orderDetailDTOList);
        cutDownPrice = BigDecimal.ZERO;
        freight = BigDecimal.ZERO;
        actualPrice = totalPrice.subtract(cutDownPrice).add(freight);

        /* 组装订单信息*/
        OrderParameter orderParameter = new OrderParameter()
                .setOrderNo(orderNo)
                .setUserName(userDTO.getUserName())
                .setReceiverName(addressDTO.getReceiverName())
                .setReceiverMobileNo(addressDTO.getReceiverMobileNo())
                .setReceiverAddress(addressDTO.getReceiverAddress())
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(10)
                .setTotalPrice(totalPrice)
                .setCutDownPrice(BigDecimal.ZERO)
                .setFreight(freight)
                .setActualPrice(BigDecimal.ZERO);

        /* 远程调用 订单入库 */
        ServiceResult<OrderDTO> saveOrderResult = orderService.saveOrder(orderParameter);

        /* 远程调用 订单详情入库 */
        ServiceResult<List<OrderDetailDTO>> saveOrderDetailResult = orderService.saveOrderDetailList(new OrderDetailListParameter().setOrderDetailDTOList(orderDetailDTOList));

        return new ServiceResult<CreateOrderResult>(ResultStatusEnum.SUCCESS, "下单成功，正在生成订单，稍后请注意查询。");
    }

    private void initOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList, String orderNo) {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            orderDetailDTO.setTotalPrice(BigDecimal.ZERO)
                    .setActualPrice(BigDecimal.ZERO)
                    .setOrderNo(orderNo);
        }
    }

    private BigDecimal calcTotalPrice(List<OrderDetailDTO> orderDetailDTOList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (ListUtil.isNotEmpty(orderDetailDTOList)) {
            for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                totalPrice = totalPrice.add(orderDetailDTO.getActualPrice());
            }
        }
        return totalPrice;
    }

}
