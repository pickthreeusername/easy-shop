package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.admin.dao.TbContentCategoryDao;
import com.cyc.easy.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryDao categoryDao;
    @Override
    public List<TbContentCategory> selectAll() {

        return categoryDao.selectAll();
    }


}
