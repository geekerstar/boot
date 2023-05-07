package com.geekerstar.rocketmq.exception;

import com.geekerstar.rocketmq.config.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author geekerstar
 * @date 2023/2/3 16:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未定义的异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleException(Exception e) {
        log.error("系统开小差了，异常信息", e);
        return Response.failed();
    }
}
