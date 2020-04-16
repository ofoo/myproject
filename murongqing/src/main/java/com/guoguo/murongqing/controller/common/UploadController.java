package com.guoguo.murongqing.controller.common;

import com.google.common.collect.Maps;
import com.guoguo.config.MurongqingProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private MurongqingProperty murongqingProperty;

    @RequestMapping("/markdown/file")
    @ResponseBody
    public Map<String, Object> uploadMarkdownFile(HttpSession session, @RequestParam("editormd-image-file") MultipartFile file, HttpServletRequest request) {
        String path = murongqingProperty.getFilePath() + "/";
        String targetFileName = uploadFile(file, path);
        String url = "/upload/" + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("success", 1);
        fileMap.put("message", "上传成功");
        fileMap.put("url", url);
        return fileMap;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/file")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = murongqingProperty.getFilePath() + "/";
        String targetFileName = uploadFile(file, path);
        String url = "/upload/" + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("url", url);
        return fileMap;
    }

    public String uploadFile(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);


        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        }
        //A:abc.jpg
        //B:abc.jpg
        return targetFile.getName();
    }
}
