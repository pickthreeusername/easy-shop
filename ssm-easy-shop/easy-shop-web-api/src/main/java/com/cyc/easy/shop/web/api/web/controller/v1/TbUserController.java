package com.cyc.easy.shop.web.api.web.controller.v1;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.path.v1}/users/")
public class TbUserController {
    @Autowired
    private TbUserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser user) {

       return userService.login(user);
    }
}

