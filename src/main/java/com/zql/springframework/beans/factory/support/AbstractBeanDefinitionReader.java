package com.zql.springframework.beans.factory.support;

import com.zql.springframework.core.io.ResourceLoader;

/**
 * @author：zql
 * @date: 2023/6/21
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefintionReader{
    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
