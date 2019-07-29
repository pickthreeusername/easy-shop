package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.commons.persistence.BaseDao;
import com.cyc.easy.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据email查询用户信息
     * @param email
     * @return
     */
    public TbUser getUserByEmail(String email);

}
