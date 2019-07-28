package com.cyc.easy.shop.domain;

import com.cyc.easy.shop.commons.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TbContentCategory extends BaseEntity {

    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private boolean isParent;


}
