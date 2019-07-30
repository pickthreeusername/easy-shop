package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbContentCategory;
import com.cyc.easy.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.cyc.easy.shop.web.admin.dao.TbContentCategoryDao;
import com.cyc.easy.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Transactional(readOnly = true)
@Service
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    @Transactional(readOnly = false)
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
                    TbContentCategory currentCategoryParent = dao.getEntityById(parent.getId());
                    currentCategoryParent.setIfParent(true);
                    dao.update(currentCategoryParent);
                }

                dao.insert(category);
                message = "新增分类成功";
            }
            //修改信息
            else{
                dao.update(category);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }
    }


}
