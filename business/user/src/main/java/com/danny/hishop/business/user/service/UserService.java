package com.danny.hishop.business.user.service;

import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.business.user.model.param.UserListParameter;
import com.danny.hishop.business.user.model.param.UserParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:18
 */
public interface UserService {
    public ServiceResult<UserDTO> findByUserName(UserParameter userParameter);

    public ServiceResult<UserDTO> saveUser(UserParameter userParameter);

    public ServiceResult<Boolean> saveUserList(UserListParameter userListParameter) throws Exception;

}
