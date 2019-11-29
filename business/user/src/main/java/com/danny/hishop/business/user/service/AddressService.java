package com.danny.hishop.business.user.service;


import com.danny.hishop.business.user.model.dto.AddressDTO;
import com.danny.hishop.business.user.model.param.AddressParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressService
 * @Description:
 * @Created on 2019-02-25 18:26:50
 */
public interface AddressService {
    ServiceResult<List<AddressDTO>> findByUserName(AddressParameter addressParameter);

    ServiceResult<List<AddressDTO>> findByUserNameAndIsDefault(AddressParameter addressParameter);

    ServiceResult<AddressDTO> saveAddress(AddressParameter addressParameter);

}
