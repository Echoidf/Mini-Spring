package com.zql.springframework;

import cn.hutool.core.io.IoUtil;
import com.zql.springframework.bean.UserDao;
import com.zql.springframework.bean.UserService;
import com.zql.springframework.beans.PropertyValue;
import com.zql.springframework.beans.PropertyValues;
import com.zql.springframework.beans.factory.config.BeanDefinition;
import com.zql.springframework.beans.factory.config.BeanReference;
import com.zql.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zql.springframework.core.io.DefaultResourceLoader;
import com.zql.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author：zql
 * @date: 2023/5/15
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        //1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        //4.第二次获取bean from singleton
        UserService singleton = (UserService) beanFactory.getSingleton("userService");
        singleton.queryUserInfo();

        System.out.println(userService == singleton); //true
    }

    //第5章，注入属性和依赖对象
    @Test
    public void test_BeanFactory2() {
        //1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.UserDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        //3.UserService设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        //4.UserService注入Dao
        beanFactory.registerBeanDefinition("userService",new BeanDefinition(UserService.class, propertyValues));

        //5.userService获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    // 第6章，从Spring.xml获取bean并注入属性

    //==================START==================
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}

