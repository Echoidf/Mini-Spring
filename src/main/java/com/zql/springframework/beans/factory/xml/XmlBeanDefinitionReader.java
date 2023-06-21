package com.zql.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.zql.springframework.beans.BeansException;
import com.zql.springframework.beans.PropertyValue;
import com.zql.springframework.beans.factory.config.BeanDefinition;
import com.zql.springframework.beans.factory.config.BeanReference;
import com.zql.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.zql.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.zql.springframework.core.io.Resource;
import com.zql.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author：zql
 * @date: 2023/6/21
 * 对 XML 文件的解析,注入bean和bean properties
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (ClassNotFoundException | IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        loadBeanDefinitions(resourceLoader.getResource(location));
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 获取class方便获取类中的名称
            Class<?> clz = Class.forName(className);

            // 优先级id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isNotEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(beanName);
            }

            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clz);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                // 解析标签 property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrVal = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值：引入对象、值对象
                Object val = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrVal;

                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, val);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
