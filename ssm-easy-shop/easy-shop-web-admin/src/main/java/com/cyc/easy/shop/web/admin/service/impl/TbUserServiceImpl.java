package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.cyc.easy.shop.web.admin.dao.TbUserDao;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {


    @Override
    public TbUser login(String email, String password) {
        TbUser user = dao.getUserByEmail(email);
        if (user != null) {
            String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (user.getPassword().equals(md5password)){
                return user;
            }
        }
        return null;
    }


    @Override
    public BaseResult save(TbUser user) {
        String validator = BeanValidator.validator(user);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            String message = null;
            user.setUpdated(new Date());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            //新增操作
            if (user.getId() == null) {
                user.setCreated(new Date());
                dao.insert(user);
                message = "新增用户成功";
            }
            //修改信息
            else{
                dao.update(user);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }
    }
}
