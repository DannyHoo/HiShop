package com.danny.hishop.business.user.controller;

import com.danny.hishop.business.user.model.dto.AddressDTO;
import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.business.user.model.param.AddressParameter;
import com.danny.hishop.business.user.model.param.UserParameter;
import com.danny.hishop.business.user.service.AddressService;
import com.danny.hishop.framework.model.enums.YesNoEnum;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/29下午3:04
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //@HystrixCommand(fallbackMethod = "hystrixGetByUserName")
    @RequestMapping(value = "/getByUserName/{userName}", method = RequestMethod.GET)
    public Response getByUserName(@PathVariable String userName) {
        ServiceResult<List<AddressDTO>> result = addressService.findByUserNameAndIsDefault(new AddressParameter().setUserName(userName).setIsDefault(YesNoEnum.YES.getCode()));
        return Response.build(result);
    }

    /**
     * getByUserName方法熔断处理
     * @param userName
     * @return
     */
    public Response hystrixGetByUserName(@PathVariable String userName) {
        ServiceResult<List<AddressDTO>> result = addressService.findByUserNameAndIsDefault(new AddressParameter().setUserName(userName).setIsDefault(YesNoEnum.YES.getCode()));
        return Response.build(result);
    }
}
