package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    public List<TbUser> selectAll();

    public TbUser login(String email, String password);
}
