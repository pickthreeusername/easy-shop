package com.cyc.easy.shop.web.admin.service;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.commons.dto.PageInfo;
import com.cyc.easy.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    public List<TbUser> selectAll();

    public TbUser login(String email, String password);

    /**
     * 保存用户信息
     * @param user
     */
    public BaseResult save(TbUser user);

    /**
     * 根据Id删除用户
     * @param id
     */
    public int delete(int id);

    public TbUser getUserById(long id);

    public List<TbUser> search(TbUser tbUser);

    public void deleteMulti(String[] ids);

    public PageInfo<TbUser> page(int start, int length, int draw, TbUser user);


}
