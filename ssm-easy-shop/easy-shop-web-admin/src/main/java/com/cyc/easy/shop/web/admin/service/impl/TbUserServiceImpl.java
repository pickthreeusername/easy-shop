package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.dao.TbUserDao;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
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

    @Override
    public BaseResult save(TbUser user) {
        BaseResult result = checkUser(user);
        if (result.getStatus() == BaseResult.STATUS_SUCCESS) {
            String message = null;
            user.setUpdated(new Date());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            //新增操作
            if (user.getId() == null) {
                user.setCreated(new Date());
                tbUserDao.insert(user);
                message = "新增用户成功";
            }
            //修改信息
            else{
                tbUserDao.update(user);
                message = "编辑成功";
            }

            result = BaseResult.success(message);
        }

        return result;
    }

    @Override
    public int delete(int id) {
        return tbUserDao.delete(id);
    }

    @Override
    public TbUser getUserById(long id) {
        return tbUserDao.getUserById(id);
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    /**
     * 验证用户信息
     * @param user
     * @return
     */
    private BaseResult checkUser(TbUser user) {
        int status = BaseResult.STATUS_SUCCESS;
        String message = null;
        //验证邮箱
        if (StringUtils.isBlank(user.getEmail())) {
            status = BaseResult.STATUS_FAIL;
            message = "用户邮箱不能为空，请重新输入";
        }

        else if (StringUtils.isBlank(user.getPassword())) {
            status = BaseResult.STATUS_FAIL;
            message = "登录密码不能为空，请重新输入";
        }

        else if (StringUtils.isBlank(user.getUsername())) {
            status = BaseResult.STATUS_FAIL;
            message = "用户姓名不能为空，请重新输入";
        }
        else if (StringUtils.isBlank(user.getPhone())) {
            status = BaseResult.STATUS_FAIL;
            message = "用户手机号不能为空，请重新输入";
        }
        return BaseResult.createResult(status, message);
    }

}
