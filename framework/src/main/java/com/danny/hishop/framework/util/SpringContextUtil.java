package com.danny.hishop.framework.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    @Data
    @Accessors(chain = true)
    public static class Pair {
        BigDecimal maxWeight=BigDecimal.ZERO;
        BigDecimal minWeight=BigDecimal.ZERO;
    }

    public static void main(String[] args) {



        List<Pair> pairList = new ArrayList<>();
        pairList.add(new Pair());
        pairList.add(new Pair());
        pairList.add(new Pair());
        BigDecimal maxWeight = BigDecimal.ZERO;
        for (int i = 1; i <= 5; i++) {
            BigDecimal minWeight = BigDecimal.ZERO;
            for (int j = 0; j < pairList.size(); j++) {
                pairList.get(j).setMinWeight(minWeight).setMaxWeight(new BigDecimal(j+1));
            }
        }

        BigDecimal skuWeight=new BigDecimal("11.01");
        final BigDecimal[] totalWeight = {BigDecimal.ZERO};
        pairList.stream().forEach(pair -> {
            totalWeight[0] = totalWeight[0].add(skuWeight.multiply(new BigDecimal(2)));
        });
    }

    public static ApplicationContext applicationContext;

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 获取当前对象的代理对象
     *
     * @param <T> 对象类型
     * @return 代理对象
     */
    public static <T> T getCurrentProxy() {
        return (T) AopContext.currentProxy();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

}