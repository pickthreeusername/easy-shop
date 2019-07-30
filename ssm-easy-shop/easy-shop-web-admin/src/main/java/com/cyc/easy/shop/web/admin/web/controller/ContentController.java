package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.web.admin.abstracts.AbstractBaseController;
import com.cyc.easy.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("content")
public class ContentController extends AbstractBaseController<TbContent, TbContentService> {

    /**
     * 将TbContent封装进模型对象
     *
     * @param id
     * @return
     */
    @ModelAttribute
    private TbContent getContent(Long id) {
        TbContent content = null;
        if (id == null) {
            content = new TbContent();
        } else {
            content = service.getEntityById(id);
        }
        return content;
    }




    /**
     * 内容列表
     *
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }

    /**
     * 跳转内容表单页面
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent content, RedirectAttributes redirectAttributes, Model model) {
        BaseResult result = service.save(content);
        //响应成功，重定向到内容列表页面
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("message", result.getMessage());
            return "redirect:/content/list";
        }
        //失败
        else {
            model.addAttribute("content", content);
            model.addAttribute("message", result.getMessage());
            return "content_form";
        }

    }




    /**
     * 查看详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        //在执行@RequestMapping方法之前， @ModelAttribute的方法已经把content绑定到model中了
        return "content_detail";
    }

}
