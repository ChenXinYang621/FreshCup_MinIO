package com.sast.minio.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-11-04 20:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFile {
    private String id;

    @TableId(value = "file_name")
    private String fileName;

    @TableLogic
    private Byte enable;
}
