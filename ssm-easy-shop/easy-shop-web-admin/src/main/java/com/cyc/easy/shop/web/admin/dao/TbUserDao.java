package com.cyc.easy.shop.web.admin.dao;

import com.cyc.easy.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {


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
     * 根据Id查询用户
     * @param id
     * @return
     */
    public TbUser getUserById(long id);



    /**
     * 批量删除用户
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 用户分页
     * @param map
     * @return
     */
    public List<TbUser> page(Map<String, Object> map);

    /**
     * 符合条件的总条数
     * @param user
     * @return
     */
    public Integer count(TbUser user);
}
