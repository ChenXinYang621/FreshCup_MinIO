package com.sast.minio.until;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sast.minio.exception.LocalRuntimeException;
import com.sast.minio.model.UserUpload;
import com.sast.minio.service.UserFileService;
import com.sast.minio.service.UserUploadService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 16:27
 **/
@Component
@Slf4j
public class MinioUtil {
    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private UserUploadService userUploadService;

    @Autowired
    private UserFileService userFileService;

    public List<String> upload(MultipartFile[] file, String id) {
        if (file == null) {
            throw new LocalRuntimeException(CustomError.FILE_NOT_FOUND);
        }
        UserUpload userUpload = userUploadService.getUserById(id);
        if (userUpload == null) {
            throw new LocalRuntimeException(CustomError.NO_USER);
        }
        List<String> name = new LinkedList<>();
        for (MultipartFile multipartFile : file) {
            String fileName = userUpload.getUsername() + "_" + userUpload.getId() + "_"
                    + multipartFile.getOriginalFilename();
            userFileService.updateFileName(id, fileName);
            InputStream in = null;
            try {
                in = multipartFile.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(in, in.available(), -1)
                        .contentType(multipartFile.getContentType())
                        .build()
                );
                name.add(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return name;
    }

    public ResponseEntity<byte[]> download(String fileName) {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, Constants.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(Collections.singletonList("*"));
            responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    public void delete(String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName)
                    .object(fileName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
