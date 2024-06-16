package com.phoenix.devops.utils;

import cn.hutool.core.lang.Assert;
import org.springframework.util.ResourceUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public class ResourceUtil {
    /**
     * 类路径下获取资源
     *
     * @param fileName 文件名或者文件路径（classpath为根路径）
     * @return 资源url
     */
    public static List<URL> getResources(String fileName) {
        Assert.notNull(fileName, "文件名不能为空");
        try {
            // 根据文件名加载所有的同名文件
            Enumeration<URL> urls = ClassUtil.getClassLoader(ResourceUtils.class).getResources(fileName);
            if (urls != null) {
                List<URL> us = new ArrayList<>();
                while (urls.hasMoreElements()) {
                    us.add(urls.nextElement());
                }
                return us;
            }
            return Collections.emptyList();
        } catch (Throwable t) {
            throw new IllegalArgumentException("获取文件资源失败", t);
        }
    }
}
