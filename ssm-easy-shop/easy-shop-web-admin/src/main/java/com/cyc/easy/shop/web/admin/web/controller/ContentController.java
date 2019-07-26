package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("content")
public class ContentController {
    @Autowired
    private TbContentService contentService;
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
            content = contentService.getContentById(id);
        }
        return content;
    }

    /**
     * 内容列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        return "content_list";
    }

    /**
     * 跳转内容表单页面
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent content, RedirectAttributes redirectAttributes, Model model) {
        BaseResult result = contentService.save(content);
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
     * 删除内容
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            contentService.deleteMulti(idArray);
            return BaseResult.success("删除成功");
        }

        return BaseResult.fail("删除失败");
    }

    /**
     * 内容列表分页
     * @param request
     * @param content
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent content) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int start = strStart == null ? 0 : Integer.valueOf(strStart);
        int length = strStart == null ? 10 : Integer.valueOf(strLength);
        int draw = strDraw == null ? 0 : Integer.valueOf(strDraw);

        PageInfo<TbContent> result = contentService.page(start, length, draw, content);
        return result;
    }

    /**
     * 查看详情
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent content, Model model) {
        //在执行@RequestMapping方法之前， @ModelAttribute的方法已经把content绑定到model中了
        return "content_detail";
    }

}
