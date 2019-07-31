package com.cyc.easy.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("upload")
public class FileUploadController {
    private final static String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile[] editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        //编辑器中的图片上传，返回对应格式的数据
        if (dropzFile == null && editorFile.length > 0) {
            
            //返回数据格式  {"errno":0, "data":[url1,url2,...]}
            List<String> data = new ArrayList<String>();
            for (MultipartFile file : editorFile) {
                data.add(writeFile(file, request));
            }
            result.put("errno", 0);
            result.put("data", data);
        }

        //dropzone文件上传
        else {
            // 返回 JSON 数据，这里只带入了文件名
            result.put("fileName", writeFile(dropzFile, request));
        }
        return result;
    }

    /**
     * 写入文件
     *
     * @param uploadFile
     * @param request
     * @return
     */
    private String writeFile(MultipartFile uploadFile, HttpServletRequest request) {
        // 获取上传的原始文件名
        String fileName = uploadFile.getOriginalFilename();
        //文件存放路径：当前项目的static/upload/文件夹下
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //文件后缀名
        String surfix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        // 判断并创建上传用的文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + surfix);

        try {
            // 写入文件
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
		// scheme:协议  servername：服务器名 port：端口号
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        // 返回文件完整路径
        return serverPath + UPLOAD_PATH + file.getName();
    }
}