package com.cyc.easy.shop.web.api.dao;

import com.cyc.easy.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao {
    public TbUser login(TbUser user);
}
