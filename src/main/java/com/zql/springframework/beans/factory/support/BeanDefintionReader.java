package com.zql.springframework.beans.factory.support;

import com.zql.springframework.beans.BeansException;
import com.zql.springframework.core.io.Resource;
import com.zql.springframework.core.io.ResourceLoader;

/**
 * @author：zql
 * @date: 2023/6/21
 */
public interface BeanDefintionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
