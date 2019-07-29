package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.commons.persistence.BaseService;
import com.cyc.easy.shop.domain.TbUser;

public interface TbUserService extends BaseService<TbUser> {

    public TbUser login(String email, String password);




}
