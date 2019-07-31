package com.cyc.easy.shop.web.api.service;

import com.cyc.easy.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    public List<TbContent> selectByCategoryId(Long categoryId);

}
