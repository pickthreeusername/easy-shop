package com.cyc.easy.shop.web.api.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.api.dao.TbUserDao;
import com.cyc.easy.shop.web.api.service.TbUserService;
import com.cyc.easy.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao userDao;
    @Override
    public BaseResult login(TbUser user) {
        TbUser tbUser = userDao.login(user);
        if (tbUser != null) {
            //密码正确
            if (tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
                TbUserDTO dto = new TbUserDTO();
                BeanUtils.copyProperties(tbUser, dto);
                return BaseResult.success("成功", dto);
            }
        }
        return BaseResult.fail("账号或密码错误");
    }
}
