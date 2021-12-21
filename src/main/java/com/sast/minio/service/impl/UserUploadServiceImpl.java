package com.sast.minio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sast.minio.mapper.UserUploadMapper;
import com.sast.minio.model.UserUpload;
import com.sast.minio.service.UserUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 17:50
 **/
@Service
@Slf4j
public class UserUploadServiceImpl implements UserUploadService {
    @Autowired
    private UserUploadMapper userUploadMapper;

    @Override
    public UserUpload getUserById(String id) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userUploadMapper.selectOne(wrapper);
    }

    @Override
    public void storeMessage(UserUpload userUpload) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper<>();
        UserUpload temp = getUserById(userUpload.getId());
        wrapper.eq("id", userUpload.getId());
        if (temp != null) {
            userUploadMapper.update(userUpload, wrapper);
        } else {
            int result = userUploadMapper.insert(userUpload);
            log.info(String.valueOf(result));
        }
    }
}
