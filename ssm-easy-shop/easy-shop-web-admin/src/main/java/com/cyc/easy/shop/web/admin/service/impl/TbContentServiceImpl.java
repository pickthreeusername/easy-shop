package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.cyc.easy.shop.web.admin.dao.TbContentDao;
import com.cyc.easy.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {

    @Override
    public BaseResult save(TbContent content) {
        String validator = BeanValidator.validator(content);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        else{
            String message = null;
            content.setUpdated(new Date());
            //新增操作
            if (content.getId() == null) {
                content.setCreated(new Date());
                dao.insert(content);
                message = "新增内容成功";
            }
            //修改信息
            else{
                dao.update(content);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }

    }






}
