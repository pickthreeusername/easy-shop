package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    /**
     * 查看所有内容分类，按parentId排序
     * @return
     */
    public List<TbContentCategory> selectAll();
    /**
     * 根据父id查找类别
     * @param pid
     * @return
     */
    public List<TbContentCategory> selectByPid(Long pid);
    /**
     * 根据 ID 查找分类
     * @param id
     * @return
     */
    public TbContentCategory getCategoryById(Long id);
}
