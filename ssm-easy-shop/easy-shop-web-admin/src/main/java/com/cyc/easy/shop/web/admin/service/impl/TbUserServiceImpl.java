package com.cyc.easy.shop.web.admin.service.impl;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.commons.validator.BeanValidator;
import com.cyc.easy.shop.domain.TbUser;
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
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

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
    public void update(TbUser entity) {
        tbUserDao.update(entity);
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
                tbUserDao.insert(user);
                message = "新增用户成功";
            }
            //修改信息
            else{
                tbUserDao.update(user);
                message = "编辑成功";
            }

            return BaseResult.success(message);
        }


    }

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public TbUser getEntityById(long id) {
        return tbUserDao.getEntityById(id);
    }





    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public Integer count(TbUser entity) {
        return tbUserDao.count(entity);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw,TbUser user) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbUser", user);
        int count = tbUserDao.count(user);

        PageInfo<TbUser> result = new PageInfo<TbUser>();
        result.setData(tbUserDao.page(params));
        result.setDraw(draw);
        result.setRecordsFiltered(count);
        result.setRecordsTotal(count);
        return result;
    }



}
