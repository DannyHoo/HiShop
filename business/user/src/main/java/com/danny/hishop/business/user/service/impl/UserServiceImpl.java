package com.danny.hishop.business.user.service.impl;

import com.danny.hishop.business.user.dao.UserDAO;
import com.danny.hishop.business.user.domain.UserDO;
import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.business.user.model.param.UserListParameter;
import com.danny.hishop.business.user.model.param.UserParameter;
import com.danny.hishop.business.user.service.UserService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public ServiceResult<UserDTO> findByUserName(UserParameter userParameter) {
        UserDO userDO = userDAO.findByUserName(userParameter.getUserName());
        UserDTO userDTO = BeanUtil.convertIgnoreNullProperty(userDO, UserDTO.class);
        return new ServiceResult<UserDTO>(ResultStatusEnum.SUCCESS, userDTO);
    }

    @Override
    public ServiceResult<UserDTO> saveUser(UserParameter userParameter) {
        UserDO userDO = BeanUtil.convertIgnoreNullProperty(userParameter, UserDO.class);
        int counts = userDAO.insertUserDO(userDO);
        if (counts == 1 && userDO.getId() != null) {
            UserDTO userDTOFound = BeanUtil.convertIgnoreNullProperty(userDO, UserDTO.class);
            return new ServiceResult<UserDTO>(ResultStatusEnum.SUCCESS, userDTOFound);
        }
        return new ServiceResult<UserDTO>(ResultStatusEnum.FAILURE);
    }

    @Override
    public ServiceResult<Boolean> saveUserList(UserListParameter userListParameter) throws Exception {
        List<UserDO> userDOList = BeanUtil.convertList(userListParameter.getUserDTOList(), UserDO.class);
        int result = userDAO.insertUserDOBatch(userDOList);
        if (result == userDOList.size()) {
            return new ServiceResult<Boolean>(ResultStatusEnum.SUCCESS, true);
        }
        return new ServiceResult<Boolean>(ResultStatusEnum.FAILURE);
    }
}
