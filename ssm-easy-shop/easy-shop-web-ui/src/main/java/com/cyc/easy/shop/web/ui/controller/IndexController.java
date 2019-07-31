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
    public String index(Model model, long id){
        List<TbContent> data = ContentsApi.ppt(id);

        model.addAttribute("data", data);
        return "index";
    }
}
