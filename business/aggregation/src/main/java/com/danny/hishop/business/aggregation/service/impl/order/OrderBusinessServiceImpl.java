package com.danny.hishop.business.aggregation.service.impl.order;


import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderDetailListParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.business.aggregation.model.result.order.CreateOrderResult;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.business.aggregation.model.user.param.AddressParameter;
import com.danny.hishop.business.aggregation.service.order.OrderBusinessService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.enums.YesNoEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.mq.MQProducer;
import com.danny.hishop.framework.mq.model.enums.MQTopicEnum;
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
    private MQProducer producer;

    private static final Lock lock = new ReentrantLock();

    @Override
    public ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter) {

        //已有数据准备
        UserDTO userDTO = createOrderParameter.getUserDTO();
        List<OrderDetailDTO> orderDetailDTOList = createOrderParameter.getOrderDetailDTOList();

        /* 查询收货人信息 */
        AddressDTO addressDTO;
        //ServiceResult<List<AddressDTO>> addressDTOServiceResult = addressService.findByUserNameAndIsDefault(new AddressParameter().setUserName(userDTO.getUserName()).setIsDefault(YesNoEnum.YES.getCode()));
        ServiceResult<List<AddressDTO>> addressDTOServiceResult = null;
        if (addressDTOServiceResult.isFail() || ListUtil.isEmpty(addressDTOServiceResult.getData())) {
            //addressDTOServiceResult = addressService.findByUserName(new AddressParameter().setUserName(userDTO.getUserName()));
            addressDTOServiceResult = null;
            if (addressDTOServiceResult.isFail() || ListUtil.isEmpty(addressDTOServiceResult.getData())) {
                return new ServiceResult<>(ResultStatusEnum.USER_ADDRESS_NOT_EXIST);
            }
        }
        addressDTO = addressDTOServiceResult.getData().get(0);

        /* 远程调用 更新商品库存 */
        String orderNo = "OD" + StringUtil.getRandomTimeStr();
        initOrderDetailDTOList(orderDetailDTOList, orderNo);

        //加锁开始
        lock.lock();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            //校验商品信息
            //ServiceResult<GoodsDTO> goodsDTOServiceResult = goodsService.findByGoodsNo(new GoodsParameter().setGoodsNo(orderDetailDTO.getGoodsNo()));
            ServiceResult<GoodsDTO> goodsDTOServiceResult = null;
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
            //ServiceResult<Boolean> saveGoodsResult = goodsService.updateGoods(BeanUtil.convertIgnoreNullProperty(goodsDTO, GoodsParameter.class).setBalance(goodsDTO.getBalance() - orderDetailDTO.getGoodsNum()));
            ServiceResult<Boolean> saveGoodsResult = null;
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
        try {
            //producer.sendMessage(new MQMessage().setMqTopicAndTag(MQTopicEnum.ORDER_SAVE).setBizValue(orderParameter));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*ServiceResult<OrderDTO> saveOrderResult = orderService.saveOrder(orderParameter);
        if (saveOrderResult.isFail() || saveOrderResult.getData() == null) {
            // TODO: 2019-02-26
        }*/
        /* 远程调用 订单详情入库 */
        try {
            //producer.sendMessage(new MQMessage().setMqTopicAndTag(MQTopicEnum.ORDER_DETAIL_SAVE).setBizValue(new OrderDetailListParameter().setOrderDetailDTOList(orderDetailDTOList)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*ServiceResult<List<OrderDetailDTO>> saveOrderDetailListResult = orderDetailService.saveOrderDetailList(new OrderDetailListParameter().setOrderDetailDTOList(orderDetailDTOList));
        if (saveOrderDetailListResult.isFail() || ListUtil.isEmpty(saveOrderDetailListResult.getData())) {
            // TODO: 2019-02-26
        }*/
        return new ServiceResult<CreateOrderResult>(ResultStatusEnum.SUCCESS, "下单成功，正在生成订单，稍后请注意查询。");
        //return new ServiceResult<CreateOrderResult>(ResultStatusEnum.SUCCESS, new CreateOrderResult().setOrderDTO(saveOrderResult.getData()).setOrderDetailDTOList(saveOrderDetailListResult.getData()));
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
