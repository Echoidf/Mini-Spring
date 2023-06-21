package com.zql.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.PropertyValue;
import com.zql.springframework.beans.PropertyValues;
import com.zql.springframework.beans.factory.config.BeanDefinition;
import com.zql.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author：zql
 * @date: 2023/5/15
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception  e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructor = null;
        Class clz = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = clz.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if(null != args && ctor.getParameterTypes().length == args.length){
                constructor = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName, constructor, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue pv : propertyValues.getPropertyValues()) {
                String name = pv.getName();
                Object value = pv.getValue();

                if(value instanceof BeanReference){
                    //A 依赖 B, 获取B的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (BeansException e) {
            throw new BeansException("Error setting property values: " + beanName);
        }

    }
}
