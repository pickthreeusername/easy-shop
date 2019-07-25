package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface TbContentDao {
    /**
     * 内容列表
     * @return
     */
    public List<TbContent> selectAll();
    /**
     * 新增内容
     * @param content
     */
    public int insert(TbContent content);

    /**
     * 更新内容信息
     * @param content
     */
    public int update(TbContent content);



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
     * @param map
     * @return
     */
    public List<TbContent> page(Map<String, Object> map);

    /**
     * 符合条件的总条数
     * @param content
     * @return
     */
    public Integer count(TbContent content);
}
