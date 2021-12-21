package com.sast.minio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sast.minio.exception.LocalRuntimeException;
import com.sast.minio.mapper.UserFileMapper;
import com.sast.minio.model.UserFile;
import com.sast.minio.service.UserFileService;
import com.sast.minio.until.CustomError;
import com.sast.minio.until.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-11-04 20:39
 **/
@Service
@Slf4j
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    private UserFileMapper userFileMapper;

    @Autowired
    MinioUtil minioUtil;

    @Override
    public void updateFileName(String id, String fileName) {
        UserFile userFile = new UserFile();
        userFile.setId(id);
        userFile.setFileName(fileName);
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", fileName);
        UserFile temp = userFileMapper.selectOne(wrapper);
        if (temp == null) {
            userFileMapper.insert(userFile);
        } else {
            userFileMapper.update(userFile, wrapper);
        }
    }

    @Override
    public void delete(String fileName) {
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", fileName);
        userFileMapper.delete(wrapper);
        minioUtil.delete(fileName);
    }

    @Override
    public List<String> getFilesById(String id) {
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).select("file_name");
        List<String> names = new LinkedList<>();
        List<UserFile> userFile = userFileMapper.selectList(wrapper);
        if (userFile.size() == 0) {
            throw new LocalRuntimeException(CustomError.NO_USER);
        }
        for (UserFile file : userFile) {
            names.add(file.getFileName());
        }
        return names;
    }
}
