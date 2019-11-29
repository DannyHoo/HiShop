package com.danny.hishop.business.aggregation.feign.user;

import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.framework.model.result.ServiceResult;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午3:48
 */
public class UserFallback implements UserService {

    @Override
    public ServiceResult<List<AddressDTO>> getAddressByUserName(String userName) {
        return null;
    }
}
