package com.cyc.easy.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TbUserDTO {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String username;
    private String phone;
}
