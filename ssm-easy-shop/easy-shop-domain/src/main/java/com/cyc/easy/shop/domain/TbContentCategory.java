package com.cyc.easy.shop.domain;

import com.cyc.easy.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class TbContentCategory extends BaseTreeEntity {
    private TbContentCategory parent;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;



}
