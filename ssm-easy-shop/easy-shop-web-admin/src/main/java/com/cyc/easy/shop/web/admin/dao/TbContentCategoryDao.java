package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    /**
     * 查看所有内容分类，按parentId排序
     * @return
     */
    public List<TbContentCategory> selectAll();
}
