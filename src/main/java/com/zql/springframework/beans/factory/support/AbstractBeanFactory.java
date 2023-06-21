package com.zql.springframework.beans.factory.support;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.factory.BeanFactory;
import com.zql.springframework.beans.factory.config.BeanDefinition;

/**
 * @author：zql
 * @date: 2023/5/14
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return (T) singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
