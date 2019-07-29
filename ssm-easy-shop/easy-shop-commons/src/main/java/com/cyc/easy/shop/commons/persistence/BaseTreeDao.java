package com.cyc.easy.shop.commons.persistence;

import java.util.List;

/**
 * 所有树形结构数据层的基类
 * @param <T>
 */
public interface BaseTreeDao<T extends BaseEntity> extends BaseDao {
    /**
     * 根据父id查找类别
     * @param pid
     * @return
     */
    public List<T> selectByPid(Long pid);
}
