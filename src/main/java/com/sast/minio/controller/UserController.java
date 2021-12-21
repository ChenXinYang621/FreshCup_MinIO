package com.sast.minio.controller;

import com.sast.minio.model.UserUpload;
import com.sast.minio.service.UserFileService;
import com.sast.minio.service.UserUploadService;
import com.sast.minio.until.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 20:26
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    MinioUtil minioUtil;

    @Autowired
    UserUploadService userUploadService;

    @Autowired
    UserFileService userFileService;

    @PostMapping("/add")
    public String addNewUser(@RequestBody UserUpload userUpload) {
        userUploadService.storeMessage(userUpload);
        return "ok";
    }

    @PostMapping("/upload")
    public List<String> upload(@RequestBody MultipartFile[] multipartFile, String id) {
        return minioUtil.upload(multipartFile, id);
    }

    @DeleteMapping("/delete")
    public String delete(String fileName) {
        userFileService.delete(fileName);
        return "ok";
    }

    @GetMapping("/user/getfile")
    public List<String> getFileById(String id) {
        return userFileService.getFilesById(id);
    }
}
