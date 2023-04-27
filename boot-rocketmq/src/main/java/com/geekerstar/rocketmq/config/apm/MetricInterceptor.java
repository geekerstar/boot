package com.geekerstar.rocketmq.config.apm;

import cn.hutool.core.util.IdUtil;
import com.geekerstar.rocketmq.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekerstar
 * @date 2023/2/3 16:19
 */
public class MetricInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestTraceId = request.getHeader(CommonConstant.TRACE_ID);
        if (StringUtils.isNotBlank(requestTraceId)) {
            MDC.put(CommonConstant.TRACE_ID, requestTraceId);
        } else {
            MDC.put(CommonConstant.TRACE_ID, IdUtil.fastSimpleUUID());
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        MDC.clear();
    }
}
