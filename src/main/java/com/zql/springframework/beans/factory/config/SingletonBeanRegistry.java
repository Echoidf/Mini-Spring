package com.zql.springframework.beans.factory.config;

/**
 * @author：zql
 * @date: 2023/5/14
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
