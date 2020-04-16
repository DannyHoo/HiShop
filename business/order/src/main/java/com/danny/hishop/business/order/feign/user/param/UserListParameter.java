package com.danny.hishop.business.order.feign.user.param;

import com.danny.hishop.business.order.feign.user.dto.UserDTO;
import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/28下午5:12
 */
@Data
@Accessors(chain = true)
public class UserListParameter extends BaseParameter {
    private List<UserDTO> userDTOList;
}
