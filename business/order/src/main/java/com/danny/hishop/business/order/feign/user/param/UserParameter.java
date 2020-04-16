package com.danny.hishop.business.order.feign.user.param;

import com.danny.hishop.framework.model.param.CommonParameter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @date 2019/11/28下午5:12
 */
@Data
@Accessors(chain = true)
public class UserParameter extends CommonParameter {
    private String userName;
    private String mobileNo;
    private String salt;
    private String password;
    private String email;
    private String realName;
    private String idCardNo;
}
