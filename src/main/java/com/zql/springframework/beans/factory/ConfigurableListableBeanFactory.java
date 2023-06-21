package com.zql.springframework.beans.factory;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zql.springframework.beans.factory.config.BeanDefinition;
import com.zql.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author：zql
 * @date: 2023/6/21
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
