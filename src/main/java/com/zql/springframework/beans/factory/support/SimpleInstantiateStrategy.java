package com.zql.springframework.beans.factory.support;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author：zql
 * @date: 2023/5/17
 */
public class SimpleInstantiateStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else{
                return beanClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }

    }
}
