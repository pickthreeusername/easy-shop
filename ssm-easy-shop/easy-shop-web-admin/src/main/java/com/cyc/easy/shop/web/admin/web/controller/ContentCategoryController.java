package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService categoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> sourceList = categoryService.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        targetList = sort(sourceList, targetList, 0L);
        model.addAttribute("categoryList", targetList);
        return "category_list";
    }

    /**
     * 排序 效果：每一个分类后面紧跟自己的子分类
     * @param sourceList 排序前的分类列表
     * @param targetList 目标分类列表
     * @param parentId 父级id
     * @return
     */
    private List<TbContentCategory> sort(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory category : sourceList) {
            if (category.getParentId().equals(parentId)) {
                targetList.add(category);

                if (category.isParent()){
                    sort(sourceList, targetList, category.getId());
                }
            }
        }
        return targetList;
    }

}
