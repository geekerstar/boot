package com.geekerstar.rabbitmq.config.web;

import com.geekerstar.rabbitmq.constant.CommonConstant;
import com.geekerstar.rabbitmq.exception.BusinessException;
import com.geekerstar.rabbitmq.exception.CommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2023/2/3 16:39
 */
@Slf4j
@ToString
@Getter
@Builder
@ApiModel("响应实体")
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 729186429762925859L;

    private static final Boolean SUCCESS = true;
    private static final Boolean FAILED = false;
    private static final String SUCCESS_CODE = "00000";
    private static final String SUCCESS_MSG = "OK";
    private static final String SUCCESS_RESULT = "操作成功!";

    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "返回码值", example = "00000")
    private String code;
    @ApiModelProperty(value = "状态信息")
    private String message;
    @ApiModelProperty(value = "链路追踪ID")
    private String traceId;
    @ApiModelProperty(value = "返回数据")
    private T results;

    public static <T> Response<String> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }

    public static <T> Response<String> success() {
        return newResponse(SUCCESS, SUCCESS_CODE, SUCCESS_MSG, SUCCESS_RESULT);
    }

    public static <T> Response<String> success(String results) {
        return newResponse(SUCCESS, SUCCESS_CODE, SUCCESS_MSG, results);
    }

    public static <T> Response<T> success(T results) {
        return newResponse(SUCCESS, SUCCESS_CODE, SUCCESS_MSG, results);
    }

    public static <T> Response<T> success(String message, T results) {
        return newResponse(SUCCESS, SUCCESS_CODE, message, results);
    }

    public static <T> Response<T> failed() {
        return failed(CommonException.SYS_ERROR);
    }

    public static <T> Response<T> failed(String code, String message) {
        return newResponse(FAILED, code, message, null);
    }

    public static <T> Response<T> failed(BusinessException e) {
        return newResponse(FAILED, e.getCode(), e.getMessage(), null);
    }

    public static <T> Response<T> failed(BusinessException e, T results) {
        return newResponse(FAILED, e.getCode(), e.getMessage(), results);
    }

    public static <T> Response<T> newResponse(Boolean success, String code, String message, T results) {
        return new Response<T>(success, code, message, MDC.get(CommonConstant.TRACE_ID), results);
    }

}

