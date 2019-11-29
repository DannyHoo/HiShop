package com.danny.hishop.business.user.domain;

import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午9:37
 */
@Data
@Accessors(chain = true)
public class AddressDO extends BaseDO {

    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

}
