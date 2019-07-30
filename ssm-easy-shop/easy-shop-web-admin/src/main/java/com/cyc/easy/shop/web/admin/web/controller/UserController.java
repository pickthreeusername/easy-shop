package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.abstracts.AbstractBaseController;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("user")
public class UserController extends AbstractBaseController<TbUser, TbUserService> {

    /**
     * 将TbUser封装进模型对象
     *
     * @param
     * @return
     */
    @ModelAttribute
    private TbUser getUser(Long id) {
//        if (user.getId() != null){
//            user = service.getEntityById(user.getId());
//        }
        TbUser user = null;
        if (id == null) {
            user = new TbUser();
        } else {
            user = service.getEntityById(id);
        }
        return user;
    }

    /**
     * 用户列表
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }

    /**
     * 跳转用户表单页面
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser user, RedirectAttributes redirectAttributes, Model model) {
        BaseResult result = service.save(user);
        //响应成功，重定向到用户列表页面
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("message", result.getMessage());
            return "redirect:/user/list";
        }
        //失败
        else {
            model.addAttribute("user", user);
            model.addAttribute("message", result.getMessage());
            return "user_form";
        }

    }

    /**
     * 查看详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        //在执行@RequestMapping方法之前， @ModelAttribute的方法已经把user绑定到model中了
        return "user_detail";
    }

}
