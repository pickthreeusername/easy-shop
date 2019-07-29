package com.cyc.easy.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有树形结构数据层的基类
 * @param <T>
 */
public interface BaseTreeDao<T extends BaseEntity>  {
    /**
     * 查询所有
     * @return
     */
    public List<T> selectAll();



    /**
     * 新增
     * @param user
     */
    public void insert(T user);

    /**
     * 更新信息
     * @param user
     */
    public void update(T user);



    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public T getEntityById(long id);



    /**
     * 批量删除
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
