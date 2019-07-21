package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private TbUserService userService;

    /**
     * 用户列表
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model) {
        List<TbUser> tbUsers = userService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

}
