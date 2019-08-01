package com.cyc.easy.shop.web.ui.api;

import com.cyc.easy.shop.commons.utils.HttpClientUtils;
import com.cyc.easy.shop.commons.utils.MapperUtils;
import com.cyc.easy.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

public class UsersApi {

    public static TbUser login(TbUser user) {
        BasicNameValuePair[] params = {new BasicNameValuePair("username", user.getUsername()),new BasicNameValuePair("password", user.getPassword())};
        String result = HttpClientUtils.doPost(API.API_USERS_LOGIN, params);

        TbUser tbUser = null;
        try {
            tbUser = MapperUtils.json2pojoByTree(result, "data", TbUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbUser;
    }
}
