package com.cyc.easy.shop.web.admin.abstracts;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.persistence.BaseDao;
import com.cyc.easy.shop.commons.persistence.BaseEntity;
import com.cyc.easy.shop.commons.persistence.BaseService;
import com.cyc.easy.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T> > implements BaseService<T> {
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
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }
    /**
     * 符合条件的总条数
     * @param entity
     * @return
     */
    public Integer count(T entity){
        return dao.count(entity);
    }
    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    public PageInfo<T> page(int start, int length, int draw, T entity){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        params.put("length", length);
        /*分页查询条件的实体类，统一叫 pageParams*/
        params.put("pageParams", entity);
        int count = dao.count(entity);

        PageInfo<T> result = new PageInfo<T>();
        result.setData(dao.page(params));
        result.setDraw(draw);
        result.setRecordsFiltered(count);
        result.setRecordsTotal(count);
        return result;
    }
}
