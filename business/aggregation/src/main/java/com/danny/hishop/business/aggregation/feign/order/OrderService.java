package com.danny.hishop.business.aggregation.feign.order;

import com.danny.hishop.business.aggregation.model.order.dto.OrderDTO;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.OrderDetailListParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午10:09
 */
@FeignClient(value = "HISHOP-ORDER", fallback = OrderFallback.class, fallbackFactory = OrderFallbackFactory.class)
public interface OrderService {

    @PostMapping("/order/order/save")
    ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter);

    @PostMapping("/order/detail/save")
    ServiceResult<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter);
}
