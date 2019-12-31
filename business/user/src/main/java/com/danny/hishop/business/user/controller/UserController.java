package com.danny.hishop.business.user.controller;

import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.business.user.model.param.UserParameter;
import com.danny.hishop.business.user.service.UserService;
import com.danny.hishop.framework.model.request.Request;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午9:36
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{userName}", method = RequestMethod.GET)
    public Response get(@PathVariable String userName) {
        ServiceResult<UserDTO> result = userService.findByUserName(new UserParameter().setUserName(userName));
        return Response.build(result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody UserParameter userParameter) throws Exception {
        ServiceResult<UserDTO> result = userService.saveUser(userParameter);
        return Response.build(result);
    }

}
