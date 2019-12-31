package com.danny.hishop.business.aggregation.feign.order;

import com.danny.hishop.business.aggregation.model.order.dto.OrderDTO;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.OrderDetailListParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午10:12
 */
@Component
public class OrderFallback implements OrderService{
    @Override
    public Response<OrderDTO> saveOrder(OrderParameter orderParameter) {
        return null;
    }

    @Override
    public Response<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter) {
        return null;
    }
}
