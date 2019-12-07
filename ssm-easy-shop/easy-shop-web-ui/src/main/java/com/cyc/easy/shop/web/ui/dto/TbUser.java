package com.cyc.easy.shop.web.ui.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TbUser {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String username;
    private String phone;
    private String verification;
}
