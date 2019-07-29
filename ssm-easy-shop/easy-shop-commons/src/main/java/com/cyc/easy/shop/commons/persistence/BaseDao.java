package com.cyc.easy.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层 的基类
 */
public interface BaseDao<T extends BaseEntity> {
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
     * 用户分页
     * @param map
     * @return
     */
    public List<T> page(Map<String, Object> map);

    /**
     * 符合条件的总条数
     * @param entity
     * @return
     */
    public Integer count(T entity);
}
