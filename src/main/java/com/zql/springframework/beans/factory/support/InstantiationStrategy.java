package com.zql.springframework.beans.factory.support;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author：zql
 * @date: 2023/5/17
 * 实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
