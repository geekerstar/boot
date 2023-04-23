package com.geekerstar.rabbitmq.config.log;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author geekerstar
 * @date 2022/1/13 16:15
 */
@Slf4j
@Aspect
@Configuration
public class WebLogAspect {
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.geekerstar.rabbitmq.config.log.Weblog)")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("***************[请求开始]***************");
        String userId = "";
        log.info("[用户ID]:{}", userId);
        log.info("[Token]:{}", request.getHeader("Authorization"));
        // 打印请求 url
        log.info("[请求路径]:{}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("[请求描述]:{}", methodDescription);
        // 打印 Http method
//        log.info("** 【请求类型】         : {}", request.getMethod());
        // 打印调用 com.hantu.controller 的全路径以及执行方法
        log.info("[调用方法]:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
//        log.info("** 【请求IP地址】       : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("[请求参数]:{}", getRequestParams(request, joinPoint));
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() {
//        log.info("***************[请求结束]***************" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        if (log.isDebugEnabled()) {
            // 打印出参
            log.debug("[响应参数]:{}", JSONUtil.toJsonStr(result));
        }
        // 执行耗时
        log.info("[请求耗时]:{} ms", System.currentTimeMillis() - startTime);
        log.info("***************[请求结束]***************" + LINE_SEPARATOR);
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(Weblog.class).description());
                }
            }
        }
        return description.toString();
    }

    private String getRequestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        StringBuilder params = new StringBuilder();
        if ("POST".equals(httpMethod) || "PUT".equals(httpMethod) || "PATCH".equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            // java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
            //  https://my.oschina.net/mengzhang6/blog/2395893
            Object[] arguments = new Object[paramsArray.length];
            for (int i = 0; i < paramsArray.length; i++) {
                if (paramsArray[i] instanceof BindingResult || paramsArray[i] instanceof ServletRequest || paramsArray[i] instanceof ServletResponse || paramsArray[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                arguments[i] = paramsArray[i];
            }
            //update-begin-author:taoyan date:20200724 for:日志数据太长的直接过滤掉
//            PropertyFilter profilter = (o, name, value) -> value == null || value.toString().length() <= 500;
//            params = new StringBuilder(JSONObject.toJSONString(arguments, profilter));
            params = new StringBuilder(JSONUtil.toJsonStr(arguments));
            //update-end-author:taoyan date:20200724 for:日志数据太长的直接过滤掉
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params.append(paramNames[i]).append(":").append(args[i]).append(" ");
                }
            }
        }
        return params.toString();
    }

}
