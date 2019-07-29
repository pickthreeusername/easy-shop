package com.cyc.easy.shop.commons.persistence;

import com.cyc.easy.shop.commons.dto.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * 所有树形结构 服务层的基类
 */
public interface BaseTreeService<T extends BaseEntity>  {
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
     * 根据父id查找类别
     * @param pid
     * @return
     */
    public List<T> selectByPid(Long pid);

}
