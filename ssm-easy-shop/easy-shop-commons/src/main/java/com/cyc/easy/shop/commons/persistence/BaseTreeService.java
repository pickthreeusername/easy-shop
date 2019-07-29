package com.cyc.easy.shop.commons.persistence;

import java.util.List;

/**
 * 所有树形结构 服务层的基类
 */
public interface BaseTreeService<T extends BaseEntity> extends BaseService<T> {
    /**
     * 根据父id查找类别
     * @param pid
     * @return
     */
    public List<T> selectByPid(Long pid);

}
