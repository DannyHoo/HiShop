package com.danny.hishop.business.aggregation.service.impl.user;


import com.danny.hishop.business.aggregation.model.result.user.LoginResult;
import com.danny.hishop.business.aggregation.model.result.user.RegisterResult;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.business.aggregation.model.user.param.LoginParameter;
import com.danny.hishop.business.aggregation.model.user.param.RegisterParameter;
import com.danny.hishop.business.aggregation.model.user.param.UserParameter;
import com.danny.hishop.business.aggregation.service.user.UserBusinessService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.MD5Util;
import com.danny.hishop.framework.util.RSAUtil;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Danny
 * @Title: UserBusinessServiceImpl
 * @Description:
 * @Created on 2018-11-26 15:43:39
 */
@Service("userBusinessService")
public class UserBusinessServiceImpl implements UserBusinessService {

    /**
     * 注册
     *
     * @param registerParameter
     * @return
     */
    @Override
    public ServiceResult<RegisterResult> register(RegisterParameter registerParameter) {
        //ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(registerParameter.getUserName()));
        ServiceResult<UserDTO> userDTOResult = null;
        if (userDTOResult.isSuccess() && userDTOResult.getData() != null) {
            return new ServiceResult(ResultStatusEnum.USER_ALREADY_EXIST);
        }
        UserDTO userDTO = new UserDTO();
        String salt = StringUtil.getStringRandom(8);//MD5加密盐值
        String decryptPassword = "";
        try {
            //decryptPassword = RSAUtil.decrypt(registerParameter.getPassword(), systemConfig.getRsaPrivateKey());
            decryptPassword = null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(ResultStatusEnum.UNKOWN_SYS_ERROR);
        }
        Map randomUserMap = RandomValueUtil.getAddress();
        String md5Password = MD5Util.md5HexTwoSourceAndSalt(decryptPassword, salt);
        userDTO.setUserName(registerParameter.getUserName())
                .setMobileNo(randomUserMap.get("tel").toString())
                .setEmail(registerParameter.getUserName() + "@163.com")
                .setSalt(salt)
                .setPassword(md5Password)
                .setRealName(randomUserMap.get("name").toString()) // TODO: 2019-02-24
                .setIdCardNo(StringUtil.getRandomNum(18));        // TODO: 2019-02-24
        //ServiceResult<UserDTO> userDTOSaveResult = userService.saveUser(BeanUtil.convertIgnoreNullProperty(userDTO, UserParameter.class));
        ServiceResult<UserDTO> userDTOSaveResult = null;
        if (userDTOSaveResult.isSuccess() && userDTOSaveResult.getData() != null) {
            return new ServiceResult(ResultStatusEnum.SUCCESS).setData(userDTOSaveResult.getData());
        }
        return new ServiceResult(ResultStatusEnum.FAILURE).setData(userDTOSaveResult.getData());
    }

    /**
     * 登录
     *
     * @param loginParameter
     * @return
     */
    @Override
    public ServiceResult<LoginResult> login(LoginParameter loginParameter) {
        //ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(loginParameter.getUserName()));
        ServiceResult<UserDTO> userDTOResult = null;
        if (userDTOResult.isFail() || userDTOResult.getData() == null) {
            return new ServiceResult(ResultStatusEnum.USERNAME_OR_PASSWORD_INVALID);
        }
        UserDTO userDTO = userDTOResult.getData();
        String decryptPassword = "";
        try {
            //decryptPassword = RSAUtil.decrypt(loginParameter.getPassword(), systemConfig.getRsaPrivateKey());
            decryptPassword = null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(ResultStatusEnum.UNKOWN_SYS_ERROR);
        }
        if (MD5Util.md5HexTwoSourceAndSalt(decryptPassword, userDTO.getSalt()).equals(userDTO.getPassword())) {
            return new ServiceResult(ResultStatusEnum.SUCCESS).setData(userDTO);
        }
        return new ServiceResult(ResultStatusEnum.USERNAME_OR_PASSWORD_INVALID);
    }

    @Override
    public ServiceResult<UserDTO> findByUserName(LoginParameter loginParameter) {
        //ServiceResult<UserDTO> userDTOResult = userService.findByUserName(new UserParameter().setUserName(loginParameter.getUserName()));
        ServiceResult<UserDTO> userDTOResult = null;
        if (userDTOResult.isFail() || userDTOResult.getData() == null) {
            return new ServiceResult(ResultStatusEnum.USER_NOT_EXIST);
        }
        return new ServiceResult<>(ResultStatusEnum.SUCCESS,userDTOResult.getData());
    }

}
