package com.cyc.easy.shop.web.admin.service.test;

import com.cyc.easy.shop.domain.TbUser;
import com.cyc.easy.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springcontext.xml","classpath:springcontext-druid.xml","classpath:springcontext-mybatis.xml"})
public class TbUserServiceImplTest {
    @Autowired
    private TbUserService userService;
    @Test
    public void testSelectAll() {
        List<TbUser> userList = userService.selectAll();
        for (TbUser user : userList) {
            System.out.println(user);
        }

    }
    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }
}
