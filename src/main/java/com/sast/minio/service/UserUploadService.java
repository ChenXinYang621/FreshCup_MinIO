package com.sast.minio.service;


import com.sast.minio.model.UserUpload;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 17:50
 **/
public interface UserUploadService {
    UserUpload getUserById(String id);

    void storeMessage(UserUpload userUpload);
}
