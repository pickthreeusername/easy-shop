package com.cyc.easy.shop.web.ui.api;

import com.cyc.easy.shop.commons.utils.HttpClientUtils;
import com.cyc.easy.shop.commons.utils.MapperUtils;
import com.cyc.easy.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {
    public static List<TbContent> ppt(long id) {
        String baseResult = HttpClientUtils.doGet(API.API_CONTENTS_PPT + id);
        List<TbContent> data = null;
        try {
            data = MapperUtils.json2listByTree(baseResult, "data", TbContent.class);
            for (TbContent content : data) {
                System.out.println(content.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
