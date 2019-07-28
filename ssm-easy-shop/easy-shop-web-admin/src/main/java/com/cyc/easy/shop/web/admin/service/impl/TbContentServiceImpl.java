package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.dao.TbContentDao;
import com.cyc.easy.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao contentDao;

    @Override
    public List<TbContent> selectAll() {
        return contentDao.selectAll();
    }

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
                contentDao.insert(content);
                message = "新增内容成功";
            }
            //修改信息
            else{
                contentDao.update(content);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }

    }

    @Override
    public TbContent getContentById(long id) {
        return contentDao.getContentById(id);
    }

    @Override
    public BaseResult deleteMulti(String[] ids) {
        return contentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent content) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbContent", content);
        int count = contentDao.count(content);

        PageInfo<TbContent> result = new PageInfo<TbContent>();
        result.setData(contentDao.page(params));
        result.setDraw(draw);
        result.setRecordsFiltered(count);
        result.setRecordsTotal(count);
        return result;
    }

    @Override
    public Integer count(TbContent content) {
        return contentDao.count(content);
    }

}
