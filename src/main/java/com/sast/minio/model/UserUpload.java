package com.sast.minio.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 15:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpload {
    private String username;

    @TableId(value = "id")
    private String id;

    private String study;
    private String major;

    @TableLogic
    private Byte enable;
}
