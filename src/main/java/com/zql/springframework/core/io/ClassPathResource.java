package com.zql.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.zql.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author：zql
 * @date: 2023/6/20
 * 通过classLoader读取classPath下的文件信息
 */
public class ClassPathResource implements Resource{
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader)null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = classLoader != null?classLoader: ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
