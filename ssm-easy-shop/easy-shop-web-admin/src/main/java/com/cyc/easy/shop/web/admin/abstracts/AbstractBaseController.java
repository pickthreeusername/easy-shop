package com.cyc.easy.shop.web.admin.abstracts;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.persistence.BaseEntity;
import com.cyc.easy.shop.commons.persistence.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController< T extends BaseEntity, S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转详情
     * @return
     */
    public abstract String detail();
    /**
     * 跳转列表页面
     * @return
     */
    public abstract String list();

    /**
     * 跳转表单页
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public  abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            return BaseResult.success("删除成功");
        }

        return BaseResult.fail("删除失败");
    }

    /**
     * 列表分页
     * @param request
     * @param entity
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public  PageInfo<T> page(HttpServletRequest request, T entity){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int start = strStart == null ? 0 : Integer.valueOf(strStart);
        int length = strStart == null ? 10 : Integer.valueOf(strLength);
        int draw = strDraw == null ? 0 : Integer.valueOf(strDraw);

        PageInfo<T> result = service.page(start, length, draw, entity);
        return result;
    }
}
