package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.utils.ConstantUtils;
import com.cyc.easy.shop.commons.utils.CookieUtils;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
//@RequestMapping("login")
public class LoginController {
    private final static String COOKIE_NAME_USER_INFO ="userInfo";
    @Autowired
    private TbUserService userService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    protected String login(HttpServletRequest req, Model model) throws ServletException, IOException {
        String userInfo = CookieUtils.getCookieValue(req, COOKIE_NAME_USER_INFO);
        //cookie中有值
        if (StringUtils.isNotBlank(userInfo)) {
            String[] userInfoArray = userInfo.split(":");
            model.addAttribute("email", userInfoArray[0]);
            model.addAttribute("password", userInfoArray[1]);
            model.addAttribute("ifRemember", true);
        }
//        req.getRequestDispatcher("login").forward(req, resp);
        return "login";
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    protected String doPost(Model model, String email, String password,String ifRemember, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("用户登录");
        boolean ifRememberFlag = ifRemember != null;
        TbUser user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("message", "用户名或密码错误");
            return "login";
        } else {
            if (ifRememberFlag) {
                CookieUtils.setCookie(req, resp, COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
            } else {
                CookieUtils.deleteCookie(req, resp, COOKIE_NAME_USER_INFO);
            }
            HttpSession session = req.getSession();
            session.setAttribute(ConstantUtils.SESSION_USER, user);
            return "redirect:/main";
        }

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        String login = null;
        try {
             login =  login(request, model);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login;
    }
}
