package com.danny.hishop.business.aggregation.model.user.dto;

import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:41
 */
@Data
@Accessors(chain = true)
public class UserDTO extends BaseDTO {
    private String userName;
    private String mobileNo;
    private String salt;
    private String password;
    private String email;
    private String realName;
    private String idCardNo;
}
