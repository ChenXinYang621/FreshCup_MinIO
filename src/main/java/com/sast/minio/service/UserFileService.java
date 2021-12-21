package com.sast.minio.service;

import com.sast.minio.model.UserFile;

import java.util.List;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-11-04 20:34
 **/
public interface UserFileService {

    void updateFileName(String id, String fileName);

    void delete(String fileName);

    List<String> getFilesById(String id);
}
