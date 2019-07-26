package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    /**
     * 查看所有内容分类，按parentId排序
     *
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
