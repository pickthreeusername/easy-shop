package com.cyc.easy.shop.commons.persistence;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * 所有service层 基类
 * @param <T>
 */
public interface BaseService<T> {
    /**
     * 更新信息
     * @param entity
     */
    public void update(T entity);
    /**
     * 保存信息
     * @param entity
     */
    public BaseResult save(T entity);

    /**
     * 查询所有
     * @return
     */
    public List<T> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T getEntityById(long id);

    /**
     * 删除
     * @param ids
     */
    public void deleteMulti(String[] ids);
    /**
     * 符合条件的总条数
     * @param entity
     * @return
     */
    public Integer count(T entity);
    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    public PageInfo<T> page(int start, int length, int draw, T entity);
}
