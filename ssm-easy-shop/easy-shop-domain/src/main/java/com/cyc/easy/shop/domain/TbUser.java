package com.cyc.easy.shop.domain;

import com.cyc.easy.shop.commons.persistence.BaseEntity;
import com.cyc.easy.shop.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码必须在 6 - 20 位之间")
    private String password;
    @Length(min = 6, max = 20, message = "用户名必须在 6 - 20 位之间")
    private String username;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;



}
