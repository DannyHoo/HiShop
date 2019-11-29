package com.danny.hishop.business.user.model.param;


import com.danny.hishop.framework.model.param.CommonParameter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Danny
 * @Title: AddressParameter
 * @Description:
 * @Created on 2019-02-25 18:25:42
 */
@Data
@Accessors(chain = true)
public class AddressParameter extends CommonParameter {

    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

}
