package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbUserDao {
    public List<TbUser> selectAll();
    public TbUser getUserByEmail(String email);
}
