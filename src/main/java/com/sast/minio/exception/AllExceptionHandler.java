package com.sast.minio.exception;

import com.sast.minio.until.CustomError;
import com.sast.minio.until.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-31 14:56
 **/
@ControllerAdvice
@Controller
public class AllExceptionHandler implements ErrorController {

    private Logger logger = LoggerFactory.getLogger("ERROR");

    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/error")
    @ResponseBody
    public HttpResponse<Void> handleException(Exception uncaughtException) {
        if (uncaughtException == null) {
            return HttpResponse.failure("No found exception!", CustomError.INTERNAL_ERROR.getCode());
        }
        uncaughtException.printStackTrace();
        if (uncaughtException instanceof LocalRuntimeException) {
            logger.error(((LocalRuntimeException) uncaughtException).getError().getErrMsg());
            LocalRuntimeException localRuntimeException = (LocalRuntimeException) uncaughtException;
            return HttpResponse.failure(localRuntimeException.getError().getErrMsg(), localRuntimeException.getError().getCode());
        } else {
            logger.error("error message", uncaughtException);
            return HttpResponse.failure(uncaughtException.getMessage(), CustomError.UNKNOWN_ERROR.getCode());
        }
    }
}
