package com.cyc.easy.shop.web.api.service.impl;

import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.api.dao.TbContentDao;
import com.cyc.easy.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao contentDao;
    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory category = new TbContentCategory();
        category.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setCategory(category);
        return contentDao.selectByCategoryId(tbContent);
    }
}
