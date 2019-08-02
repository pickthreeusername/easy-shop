package com.cyc.easy.shop.web.ui.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.utils.EmailSendUtils;
import com.cyc.easy.shop.web.ui.api.UsersApi;
import com.cyc.easy.shop.web.ui.constant.SystemsConstant;
import com.cyc.easy.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private EmailSendUtils emailSendor;
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     * @param user
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser user, HttpServletRequest request, Model model) {
        if (!checkVerification(user, request)){
            model.addAttribute("baseResult", BaseResult.fail("验证码输入错误！"));
            return "login";
        }
        TbUser tbUser = UsersApi.login(user);
        //失败
        if (tbUser == null) {
            model.addAttribute("baseResult", BaseResult.fail("账户或密码错误"));
            return "login";
        }
        //成功，保存用户信息到session
        request.getSession().setAttribute(SystemsConstant.SESSION_USER_KEY, tbUser);
        //发送邮件
        try {
            emailSendor.send("账户安全", String.format("欢迎 %s 登录",user.getUsername()), "827099633@qq.com");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:index";

    }

    private boolean checkVerification(TbUser user, HttpServletRequest request) {
        if (StringUtils.equals(user.getVerification(), (CharSequence) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return true;
        }
        return false;
    }
}

