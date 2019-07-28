package com.cyc.easy.shop.domain;

import com.cyc.easy.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class TbContent extends BaseEntity {
    @NotNull(message = "所属类目不能为空")
    private Long categoryId;
    @Length(min = 1, max = 20, message = "标题必须介于 1 - 20 个字符之间")
    private String title;
    @Length(min = 1, max = 20, message = "副标题必须介于 1 - 20 个字符之间")
    private String subTitle;
    @Length(min = 1, max = 50, message = "标题描述必须介于 1 - 50 个字符之间")
    private String titleDesc;

    private String url;

    private String pic;
    private String pic2;
    @NotBlank(message = "内容不能为空")
    private String content;

}
