package com.geekerstar.sharding.exception;

import com.geekerstar.sharding.config.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2023/2/3 16:40
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

    /**
     * 手动抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleBusinessException(BusinessException e) {
        log.error("错误码值{},错误信息:{}", e.getCode(), e.getMessage());
        e.printStackTrace();
        return Response.failed(e.getCode(), e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e MethodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        StringBuilder message = new StringBuilder();
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        for (FieldError error : fieldErrors) {
//            message.append(error.getField()).append(":").append(error.getDefaultMessage()).append(",");
//        }
//        message = new StringBuilder(message.substring(0, message.length() - 1));
//        log.error(message.toString());
//        return Response.failed("G10001", message.toString());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Response.failed("G10001", message);
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(":").append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return Response.failed("G10002", message.toString());
    }

    /**
     * 处理单个参数校验失败抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return Response.failed("G10003", collect.toString());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<?> httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("错误信息:{}", e.getLocalizedMessage());
        return Response.failed(CommonException.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("错误信息{}", e.getLocalizedMessage());
        return Response.failed(CommonException.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION);
    }

    /**
     * 主键重复
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Response<?> duplicateKeyException(SQLIntegrityConstraintViolationException e) {
        log.error("错误信息:{}", e.getLocalizedMessage());
        e.printStackTrace();
        return Response.failed(CommonException.DUPLICATE_KEY_EXCEPTION);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<?> httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("错误信息:{}", e.getLocalizedMessage());
        return Response.failed(CommonException.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("错误信息:{}", e.getLocalizedMessage());
        return Response.failed(CommonException.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
    }
}




