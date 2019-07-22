package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private TbUserService userService;

    /**
     * 将TbUser封装近表单模型对象
     * @param id
     * @return
     */
    @ModelAttribute
    private TbUser getUser(Long id) {
        TbUser user = null;
        if (id == null) {
            user = new TbUser();
        }
        else{
            user = userService.getUserById(id);
        }
        return user;
    }


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

    /**
     * 跳转用户表单页面
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String sava(TbUser user, RedirectAttributes redirectAttributes, Model model) {
        BaseResult result = userService.save(user);
        //响应成功，重定向到用户列表页面
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("message", result.getMessage());
            return "redirect:/user/list";
        }
        //失败
        else{
            model.addAttribute("user", user);
            model.addAttribute("message", result.getMessage());
            return "user_form";
        }


    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(TbUser user, Model model) {
        List<TbUser> tbUsers = userService.search(user);
        model.addAttribute("tbUsers", tbUsers);
        System.out.println("test hot deploy.....");
        return "user_list";
    }


}
