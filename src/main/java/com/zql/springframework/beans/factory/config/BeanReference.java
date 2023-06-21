package com.zql.springframework.beans.factory.config;

/**
 * @author：zql
 * @date: 2023/5/19
 *
 * Bean的引用
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
