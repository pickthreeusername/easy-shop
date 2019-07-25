package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.domain.TbUser;

public interface TbUserService {

    public TbUser login(String email, String password);

    /**
     * 保存用户信息
     * @param user
     */
    public BaseResult save(TbUser user);



    public TbUser getUserById(long id);


    public void deleteMulti(String[] ids);

    public PageInfo<TbUser> page(int start, int length, int draw, TbUser user);


}
