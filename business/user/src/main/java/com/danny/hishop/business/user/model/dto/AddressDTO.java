package com.danny.hishop.business.user.model.dto;


import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Danny
 * @Title: AddressDTO
 * @Description:
 * @Created on 2019-02-25 18:33:21
 */
@Data
@Accessors(chain = true)
public class AddressDTO extends BaseDTO {
    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;

}
