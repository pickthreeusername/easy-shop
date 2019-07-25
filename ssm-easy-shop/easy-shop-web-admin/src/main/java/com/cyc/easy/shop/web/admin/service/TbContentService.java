package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    /**
     * 内容列表
     * @return
     */
    public List<TbContent> selectAll();

    /**
     * 保存内容信息
     *
     * @param content
     */
    public BaseResult save(TbContent content);


    /**
     * 根据Id查询内容
     * @param id
     * @return
     */
    public TbContent getContentById(long id);



    /**
     * 批量删除内容
     * @param ids
     */
    public BaseResult deleteMulti(String[] ids);

    /**
     * 内容分页
     * @param
     * @return
     */
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent content);

    /**
     * 符合条件的总条数
     * @param content
     * @return
     */
    public Integer count(TbContent content);
}
