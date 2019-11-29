package com.danny.hishop.business.aggregation.service.user;


import com.danny.hishop.business.aggregation.model.result.user.LoginResult;
import com.danny.hishop.business.aggregation.model.result.user.RegisterResult;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.business.aggregation.model.user.param.LoginParameter;
import com.danny.hishop.business.aggregation.model.user.param.RegisterParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: UserBusinessService
 * @Description:
 * @Created on 2018-11-26 15:41:47
 */
public interface UserBusinessService {

    public ServiceResult<RegisterResult> register(RegisterParameter registerParameter);

    public ServiceResult<LoginResult> login(LoginParameter loginParameter);

    public ServiceResult<UserDTO> findByUserName(LoginParameter loginParameter);

}
