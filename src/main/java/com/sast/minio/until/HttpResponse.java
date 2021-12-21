package com.sast.minio.until;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-31 10:44
 **/
@Data
@AllArgsConstructor
public class HttpResponse<T> {
    private boolean success;
    private String errMsg;
    private Integer errCode;
    private T data;

    public static <T> HttpResponse success(T data) {
        return new HttpResponse<>(true, null, null, data);
    }

    public static HttpResponse<Void> failure(String errMsg, Integer errCode) {
        return new HttpResponse<>(false, errMsg, errCode, null);
    }
}
