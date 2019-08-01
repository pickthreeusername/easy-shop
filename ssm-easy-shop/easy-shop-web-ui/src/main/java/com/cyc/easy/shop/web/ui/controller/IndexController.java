package com.cyc.easy.shop.web.ui.controller;

import com.cyc.easy.shop.web.ui.api.ContentsApi;
import com.cyc.easy.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model){
        //89：幻灯片类目id
        requestPPT(model, 89);
        return "index";
    }

    /**
     * 请求幻灯片
     * @param model
     * @param id
     */
    private void requestPPT(Model model,long id) {
        List<TbContent> data = ContentsApi.ppt(id);
        model.addAttribute("ppt", data);
    }
}
