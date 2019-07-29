package com.cyc.easy.shop.web.admin.web.controller;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService categoryService;

    /**
     * 将TbContentCategory封装进模型对象
     *
     * @param id
     * @return
     */
    @ModelAttribute
    private TbContentCategory getContentCategory(Long id) {
        TbContentCategory category = null;
        if (id == null) {
            category = new TbContentCategory();
        } else {
            category = categoryService.getEntityById(id);
        }
        return category;
    }


    /**
     * 分类列表
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> sourceList = categoryService.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        targetList = sort(sourceList, targetList, 0L);
        model.addAttribute("categoryList", targetList);
        return "content_category_list";
    }

    /**
     * 排序 效果：每一个分类后面紧跟自己的子分类
     * @param sourceList 排序前的分类列表
     * @param targetList 目标分类列表
     * @param parentId 父级id
     * @return
     */
    private List<TbContentCategory> sort(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory tbContentCategory : sourceList) {

            if (tbContentCategory.getParent().getId().equals(parentId)) {
                targetList.add(tbContentCategory);

                if (tbContentCategory.isIfParent()){
                    sort(sourceList, targetList, tbContentCategory.getId());
                }
            }
        }
        return targetList;
    }

    /**
     * zTree 根据父id查找对应子节点
     * @param id
     * @return
     */
    @RequestMapping(value = "tree/data")
    @ResponseBody
    public List<TbContentCategory> treeData(Long id) {

        if (id == null) {
            id = 0L;
        }
        return categoryService.selectByPid(id);
    }

    /**
     * 跳转表单页面
     *
     * @param tbContentCategory
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        //处理乱码，设置父级名称编码
        TbContentCategory parent = tbContentCategory.getParent();
        if (parent != null && parent.getName() != null){
            try{
                String parentName = new String(parent.getName().getBytes("ISO8859-1"),"UTF-8");
                parent.setName(parentName);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }

        return "content_category_form";
    }

    /**
     * 保存
     * @param category
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory category, RedirectAttributes redirectAttributes, Model model) {
        BaseResult result = categoryService.save(category);
        //响应成功，重定向到内容列表页面
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("message", result.getMessage());
            return "redirect:/content/category/list";
        }
        //失败
        else {
            model.addAttribute("category", category);
            model.addAttribute("message", result.getMessage());
            return "content_category_form";
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
            //categoryService.deleteMulti(idArray);
            return BaseResult.success("删除成功");
        }

        return BaseResult.fail("删除失败");
    }

}
