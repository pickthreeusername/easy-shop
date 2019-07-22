package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbUserDao {
    /**
     * 用户列表
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 根据email查询用户信息
     * @param email
     * @return
     */
    public TbUser getUserByEmail(String email);

    /**
     * 新增用户
     * @param user
     */
    public int insert(TbUser user);

    /**
     * 更新用户信息
     * @param user
     */
    public int update(TbUser user);

    /**
     * 根据Id删除用户
     * @param id
     */
    public int delete(long id);

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    public TbUser getUserById(long id);

    /**
     * 根据条件搜索用户信息
     * @param tbUser
     * @return
     */
    public List<TbUser> search(TbUser tbUser);
}
