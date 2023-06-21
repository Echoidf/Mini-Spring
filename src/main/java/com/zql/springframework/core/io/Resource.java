package com.zql.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author：zql
 * @date: 2023/6/20
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
