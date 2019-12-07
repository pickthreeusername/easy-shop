package com.cyc.easy.shop.web.api.service;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;

public interface TbUserService {
    public BaseResult login(TbUser user);
}
