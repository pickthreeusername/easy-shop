package com.cyc.easy.shop.web.admin.service.test;

import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springcontext.xml","classpath:springcontext-druid.xml","classpath:springcontext-mybatis.xml"})
public class TbUserServiceImplTest {
    @Autowired
    private TbUserService userService;

    @Test
    public void  testInsert() {
        TbUser user = new TbUser();
        user.setEmail("test@123.com");
        user.setUsername("test");
        user.setPassword("12332");
        user.setCreated(new Date());
        user.setUpdated(new Date());
//        int row = userService.insert(user);
//        System.out.println(row);
    }
    @Test
    public void testUpdate() {
        TbUser user = new TbUser();
        user.setEmail("test@123.com");
        user.setUsername("test UPDATE");
        user.setPassword("12332");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setId(37L);
//        int row = userService.update(user);
//        System.out.println(row);
    }
    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }
}
