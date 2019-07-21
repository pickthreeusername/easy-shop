package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private TbUserService userService;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }
}
