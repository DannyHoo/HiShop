package com.danny.hishop.business.user.service.impl;


import com.danny.hishop.business.user.dao.AddressDAO;
import com.danny.hishop.business.user.domain.AddressDO;
import com.danny.hishop.business.user.model.dto.AddressDTO;
import com.danny.hishop.business.user.model.param.AddressParameter;
import com.danny.hishop.business.user.service.AddressService;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Danny
 * @Title: AddressServiceImpl
 * @Description:
 * @Created on 2019-02-25 18:28:39
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;


    @Override
    public ServiceResult<List<AddressDTO>> findByUserName(AddressParameter addressParameter) {
        List<AddressDO> addressDOList=addressDAO.findByUserName(addressParameter.getUserName());
        List<AddressDTO> addressDTOList= BeanUtil.convertList(addressDOList,AddressDTO.class);
        return new ServiceResult<List<AddressDTO>>(ResultStatusEnum.SUCCESS, addressDTOList);
    }

    @Override
    public ServiceResult<List<AddressDTO>> findByUserNameAndIsDefault(AddressParameter addressParameter) {
        List<AddressDO> addressDOList=addressDAO.findByUserNameAndIsDefault(addressParameter.getUserName(),addressParameter.getIsDefault());
        List<AddressDTO> addressDTOList= BeanUtil.convertList(addressDOList,AddressDTO.class);
        return new ServiceResult<List<AddressDTO>>(ResultStatusEnum.SUCCESS, addressDTOList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult<AddressDTO> saveAddress(AddressParameter addressParameter) {
        AddressDO addressDO=BeanUtil.convertIgnoreNullProperty(addressParameter,AddressDO.class);
        int counts =addressDAO.saveAddress(addressDO);
        if (counts==1){
            AddressDTO addressDTO=BeanUtil.convertIgnoreNullProperty(addressDO,AddressDTO.class);
            return new ServiceResult<AddressDTO>(ResultStatusEnum.SUCCESS,addressDTO);
        }else{
            return new ServiceResult<>(ResultStatusEnum.FAILURE);
        }
    }


}
