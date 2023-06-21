package com.zql.springframework.core.io;

/**
 * @author：zql
 * @date: 2023/6/21
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
