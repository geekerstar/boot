package com.geekerstar.function.module.limit.config;

import cn.hutool.core.util.ObjectUtil;
import com.geekerstar.function.exception.BusinessException;
import com.geekerstar.function.module.limit.constant.LimitConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2023/2/6 16:59
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RequestLimitAspect {

    private final StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.geekerstar.function.module.limit.config.RequestLimit)")
    public void RequestLimit() {
    }

    @Before("RequestLimit() && @annotation(requestLimit)")
    public void requestOverTimes(JoinPoint joinPoint, RequestLimit requestLimit) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String servletPath = request.getServletPath();
        String ipAdrress = getIpAdrress(request);
        String redisKey = LimitConstant.IPS_REQUEST_LIMIT_KEY.concat(servletPath).concat(":" + ipAdrress);
        log.info("ip地址{},url路径{},path:{}", ipAdrress, url, servletPath);
        int size = redisTemplate.keys(LimitConstant.IPS_REQUEST_LIMIT_KEY.concat(servletPath).concat("*")).size();
        int limitCount;
        if (ObjectUtil.equals(redisTemplate.opsForValue().get(LimitConstant.IPS_REQUEST_LIMIT_FIXED_USED_KEY),"true")) {
            limitCount = Integer.valueOf(redisTemplate.opsForValue().get(LimitConstant.IPS_REQUEST_LIMIT_FIXED_KEY));
        } else {
            limitCount = requestLimit.count();
        }
        if (size < limitCount) {
            redisTemplate.opsForValue().set(redisKey,ipAdrress, requestLimit.time(), TimeUnit.MILLISECONDS);
        } else {
            throw new BusinessException("当前人数较多排队中，请稍等....");
        }
    }

    public static String getIpAdrress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    log.error("根据网卡获取本机配置的IP异常=>", e.getMessage());
                }
                if (inet.getHostAddress() != null) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}

