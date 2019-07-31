package com.cyc.easy.shop.web.api.web.controller.v1;

import com.cyc.easy.shop.commons.dto.BaseResult;
import com.cyc.easy.shop.domain.TbContent;
import com.cyc.easy.shop.web.api.service.TbContentService;
import com.cyc.easy.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService contentService;

    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id") long id) {
        List<TbContent> tbContents = contentService.selectByCategoryId(id);
        List<TbContentDTO> tbContentDTOS = null;
        if (tbContents != null && tbContents.size() > 0) {

            tbContentDTOS = new ArrayList<>();

            for (TbContent content : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                //实体类转数据传输对象
                BeanUtils.copyProperties(content, dto);
                tbContentDTOS.add(dto);
            }
        }


        return BaseResult.success("成功", tbContentDTOS);
    }

}
