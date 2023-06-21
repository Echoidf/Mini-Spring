package com.zql.springframework.beans.factory.config;

import com.zql.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author：zql
 * @date: 2023/6/21
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
