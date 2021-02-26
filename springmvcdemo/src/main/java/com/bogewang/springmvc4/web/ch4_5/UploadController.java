package com.bogewang.springmvc4.web.ch4_5;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;

/**
 * Created by bogewang on 2017/7/11.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file){     //1 接收上传文件
        try {
            FileUtils.writeByteArrayToFile(new File("e:/upload/" + file.getOriginalFilename()),file.getBytes());    //2 快速写文件到磁盘
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
