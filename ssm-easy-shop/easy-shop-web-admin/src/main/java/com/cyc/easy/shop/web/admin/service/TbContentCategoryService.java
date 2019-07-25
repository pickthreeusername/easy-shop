package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    /**
     * 查看所有内容分类，按parentId排序
     * @return
     */
    public List<TbContentCategory> selectAll();
}
