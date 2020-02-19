package com.danny.hishop.business.aggregation.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author huyuyang
 * @date 2020/1/6下午9:39
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.danny.hishop.business.aggregation.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution(* com.danny.hishop.business.aggregation.service.*.*(..))")
    public void servicePointcut() {
    }

    @Around("controllerPointcut()")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String[] argNames=((MethodSignature)joinPoint.getSignature()).getParameterNames();
        List<Pair<String,Object>> printParamArgs=new ArrayList<>();
        Map<String, String> headers = new HashMap<>(0);
        if (ArrayUtils.isNotEmpty(args) || ArrayUtils.isNotEmpty(argNames)){
            for (int i=0;i<args.length;i++ ) {
                //ServletRequest、ServletResponse不能序列化不能序列化，从入参里排除，否则报异常
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                    if (args[i] instanceof HttpServletRequest) {
                        Enumeration<String> headerNames = ((HttpServletRequest) args[i]).getHeaderNames();
                        while (headerNames.hasMoreElements()) {
                            String name = headerNames.nextElement();
                            String value = ((HttpServletRequest) args[i]).getHeader(name);
                            headers.put(name, value);
                        }
                    }
                    continue;
                }
                printParamArgs.add(Pair.of(argNames[i],args[i]));
            }
        }
        Object result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            long consumeTime = System.currentTimeMillis() - startTime;
            log.info("目标方法-{}.{} 入参-{} header-{} 执行结果-{} 耗时-{}", clazzName, methodName, JSON.toJSONString(printParamArgs), JSON.toJSONString(headers), JSON.toJSONString(result), consumeTime);
        } catch (Throwable e) {
            log.info("目标方法-{}.{} 入参-{} 调用异常-{}", clazzName, methodName, JSON.toJSONString(printParamArgs), e.getMessage());
            throw e;
        }
        return result;
    }

    @Around("servicePointcut()")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String[] argNames=((MethodSignature)joinPoint.getSignature()).getParameterNames();
        List<Pair<String,Object>> printParamArgs=new ArrayList<>();
        Map<String, String> headers = new HashMap<>(0);
        if (ArrayUtils.isNotEmpty(args) || ArrayUtils.isNotEmpty(argNames)){
            for (int i=0;i<args.length;i++ ) {
                //ServletRequest、ServletResponse不能序列化不能序列化，从入参里排除，否则报异常
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                    if (args[i] instanceof HttpServletRequest) {
                        Enumeration<String> headerNames = ((HttpServletRequest) args[i]).getHeaderNames();
                        while (headerNames.hasMoreElements()) {
                            String name = headerNames.nextElement();
                            String value = ((HttpServletRequest) args[i]).getHeader(name);
                            headers.put(name, value);
                        }
                    }
                    continue;
                }
                printParamArgs.add(Pair.of(argNames[i],args[i]));
            }
        }
        Object result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            long consumeTime = System.currentTimeMillis() - startTime;
            log.info("目标方法-{}.{} 入参-{} header-{} 执行结果-{} 耗时-{}", clazzName, methodName, JSON.toJSONString(printParamArgs), JSON.toJSONString(headers), JSON.toJSONString(result), consumeTime);
        } catch (Throwable e) {
            log.info("目标方法-{}.{} 入参-{} 调用异常-{}", clazzName, methodName, JSON.toJSONString(printParamArgs), e.getMessage());
            throw e;
        }
        return result;
    }
}
