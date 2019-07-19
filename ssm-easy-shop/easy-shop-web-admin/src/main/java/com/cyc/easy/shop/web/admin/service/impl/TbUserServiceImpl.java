package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.dao.TbUserDao;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser user = tbUserDao.getUserByEmail(email);
        if (user != null) {
            String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (user.getPassword().equals(md5password)){
                return user;
            }
        }
        return null;
    }

}
