package com.danny.hishop.business.order.model.param;


import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailListParameter
 * @Description:
 * @Created on 2019-02-26 16:38:05
 */
@Data
@Accessors(chain = true)
public class OrderDetailListParameter extends BaseParameter {

    private List<OrderDetailDTO> orderDetailDTOList;
}
