package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content")
public class ContentController {
    @Autowired
    private TbContentService contentService;

}
