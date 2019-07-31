package com.cyc.easy.shop.web.api.dao;

import com.cyc.easy.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbContentDao {
    public List<TbContent> selectByCategoryId(TbContent tbContent);
}
