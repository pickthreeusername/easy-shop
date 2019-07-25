package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
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
        BaseResult result = checkContent(content);
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
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

            result = BaseResult.success(message);
        }

        return result;
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

    /**
     * 验证content信息
     * @param content
     * @return
     */
    private BaseResult checkContent(TbContent content) {
        int status = BaseResult.STATUS_SUCCESS;
        String message = null;
        //验证邮箱
        if (content.getCategoryId() != null) {
            status = BaseResult.STATUS_FAIL;
            message = "分类id不能为空，请重新输入";
        }

        else if (StringUtils.isBlank(content.getTitle())) {
            status = BaseResult.STATUS_FAIL;
            message = "内容标题不能为空，请重新输入";
        }

        else if (StringUtils.isBlank(content.getSubTitle())) {
            status = BaseResult.STATUS_FAIL;
            message = "副标题不能为空，请重新输入";
        }
        else if (StringUtils.isBlank(content.getTitleDesc())) {
            status = BaseResult.STATUS_FAIL;
            message = "标题描述不能为空，请重新输入";
        }
        else if (StringUtils.isBlank(content.getContent())) {
            status = BaseResult.STATUS_FAIL;
            message = "内容详情不能为空，请重新输入";
        }
        return BaseResult.createResult(status, message);
    }
}
