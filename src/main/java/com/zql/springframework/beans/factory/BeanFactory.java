package com.zql.springframework.beans.factory;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 * @author：zql
 * @date: 2023/4/26
 */
public interface BeanFactory {
    public Object getBean(String name) throws BeansException;

    public Object getBean(String name, Object... args);
}
