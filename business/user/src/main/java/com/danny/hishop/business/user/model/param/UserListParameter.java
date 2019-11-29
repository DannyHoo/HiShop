package com.danny.hishop.business.user.model.param;

import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.framework.model.param.BaseParameter;
import com.danny.hishop.framework.model.param.CommonParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/28下午5:12
 */
@Data
@Accessors(chain = true)
public class UserListParameter extends BaseParameter {
    private List<UserDTO> userDTOList;
}
