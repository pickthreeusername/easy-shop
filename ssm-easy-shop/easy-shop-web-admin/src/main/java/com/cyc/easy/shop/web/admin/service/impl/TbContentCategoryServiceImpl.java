package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.admin.dao.TbContentCategoryDao;
import com.cyc.easy.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryDao categoryDao;

    @Override
    public List<TbContentCategory> selectAll() {

        return categoryDao.selectAll();
    }

    @Override
    public TbContentCategory getEntityById(long id) {
        return categoryDao.getEntityById(id);
    }

    @Override
    public void deleteMulti(String[] ids) {

    }

    @Override
    public Integer count(Object entity) {
        return null;
    }

    @Override
    public PageInfo page(int start, int length, int draw, Object entity) {
        return null;
    }

    @Override
    public Integer count(TbContentCategory entity) {
        return null;
    }

    @Override
    public PageInfo<TbContentCategory> page(int start, int length, int draw, TbContentCategory entity) {
        return null;
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return categoryDao.selectByPid(pid);
    }

    @Override
    public void update(TbContentCategory entity) {

    }

    @Override
    public BaseResult save(TbContentCategory category) {
        String validator = BeanValidator.validator(category);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        else{
            String message = null;
            category.setUpdated(new Date());
            //如果没有选择父级节点，则默认设置为根节点
            TbContentCategory parent = category.getParent();
            //parent不会为null
            if ( parent.getId() == null){
                //0代表根目录
                parent.setId(0L);

            }
            //新增操作
            if (category.getId() == null) {
                category.setCreated(new Date());
                //新增的节点没有子节点
                category.setIfParent(false);

                if (!parent.getId().equals(0L)){
                    //将新增节点的父级节点 的ifParent属性更新
                    TbContentCategory currentCategoryParent = categoryDao.getEntityById(parent.getId());
                    currentCategoryParent.setIfParent(true);
                    categoryDao.update(currentCategoryParent);
                }

                categoryDao.insert(category);
                message = "新增分类成功";
            }
            //修改信息
            else{
                categoryDao.update(category);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }

    }

}
