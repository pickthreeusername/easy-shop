package com.cyc.easy.shop.web.admin.abstracts;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.persistence.BaseEntity;
import com.cyc.easy.shop.commons.persistence.BaseTreeDao;
import com.cyc.easy.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {
    @Autowired
    protected D dao;
    /**
     * 更新信息
     * @param entity
     */
    @Override
    public void update(T entity){
        dao.update(entity);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public T getEntityById(long id){
        return dao.getEntityById(id);
    }

    /**
     * 删除
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }

    /**
     * 根据父id查找类别
     * @param pid
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid){
        return dao.selectByPid(pid);
    }

}
