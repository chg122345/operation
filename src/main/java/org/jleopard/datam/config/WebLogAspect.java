/**
 * @author (c) 2018,
 * @date 2018/11/16  15:46
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jleopard.datam.annotation.SysLog;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面日志收集
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* org.jleopard.datam.controller..*.*(..))")
    public void sysLogPoint() {
    }

    @Before("sysLogPoint() && @annotation(slog)")
    public void doUserBefore(JoinPoint joinPoint, SysLog slog) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求目的 TARGET ：" + slog.value());
        log.info("请求地址 URL : " + request.getRequestURL().toString());
        log.info("请求方式 HTTP_METHOD : " + request.getMethod());
        log.info("请求ip IP : " + request.getRemoteAddr());
        log.info("执行方法 CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("方法参数 ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "sysLogPoint() && @annotation(slog)")
    public void doUserAfterReturning(Object ret, SysLog slog) throws Throwable {
        // 处理完请求，返回内容
        log.info("响应内容 RESPONSE : " + ret);
        log.info("花费时长 SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
