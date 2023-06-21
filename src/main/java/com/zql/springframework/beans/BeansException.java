package com.zql.springframework.beans;

/**
 * @author：zql
 * @date: 2023/5/14
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
