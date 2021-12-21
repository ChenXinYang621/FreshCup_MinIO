package com.sast.minio.until;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-31 10:41
 **/
@AllArgsConstructor
@Getter
public enum CustomError {
    NO_USER(4000, "没有找到用户"),
    FILE_ERROR(4001, "文件类型错误"),
    FILE_NOT_FOUND(4002, "未找到文件"),
    INTERNAL_ERROR(5001, "内部出错"),
    UNKNOWN_ERROR(5002, "未知错误");

    private int code;
    private String errMsg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
