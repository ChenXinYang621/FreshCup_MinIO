package com.sast.minio.exception;

import com.sast.minio.until.CustomError;
import lombok.Getter;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-31 11:00
 **/
@Getter
public class LocalRuntimeException extends RuntimeException{
    private CustomError error;

    public LocalRuntimeException(CustomError error) {
        this.error = error;
    }

    public LocalRuntimeException(String message, CustomError error) {
        super(message);
        this.error = error;
    }

    public LocalRuntimeException(String message, Throwable cause, CustomError error) {
        super(message, cause);
        this.error = error;
    }
}
