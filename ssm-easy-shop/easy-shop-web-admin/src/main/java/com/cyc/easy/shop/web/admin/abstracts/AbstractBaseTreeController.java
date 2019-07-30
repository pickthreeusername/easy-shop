package com.cyc.easy.shop.web.admin.abstracts;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.persistence.BaseTreeEntity;
import com.cyc.easy.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);
    /**
     * 排序 效果：每一个分类后面紧跟自己的子分类
     * @param sourceList 排序前的分类列表
     * @param targetList 目标分类列表
     * @param parentId 父级id
     * @return
     */
    protected List<T> sort(List<T> sourceList, List<T> targetList, Long parentId) {
        for (T currentEntity : sourceList) {

            if (currentEntity.getParent().getId().equals(parentId)) {
                targetList.add(currentEntity);

                if (currentEntity.isIfParent()){
                    sort(sourceList, targetList, currentEntity.getId());
                }
            }
        }
        return targetList;
    }
    /**
     * zTree 根据父id查找对应子节点
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id) ;

    /**
     * 跳转表单页面
     *
     * @param
     * @return
     */
    public abstract String form(T entity);


    /**
     * 保存
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);
    /**
     * 删除内容
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);
}
