package com.geekerstar.mqtt.exception;

/**
 * @author geekerstar
 * @date 2023/2/3 16:41
 */
public class CommonException {

    public static final BusinessException SYS_ERROR = new BusinessException("A0001", "系统开小差了，请稍后重试");
    public static final BusinessException HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = new BusinessException("G10001", "请求方式不支持");
    public static final BusinessException METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = new BusinessException("G10001", "参数格式错误");
    public static final BusinessException HTTP_MESSAGE_NOT_READABLE_EXCEPTION = new BusinessException("G10001", "参数格式错误");
    public static final BusinessException DUPLICATE_KEY_EXCEPTION = new BusinessException("G10001", "数据已存在");
    public static final BusinessException MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = new BusinessException("G10001", "缺少必要参数");
}
