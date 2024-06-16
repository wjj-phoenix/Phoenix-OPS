package com.phoenix.devops.utils;

import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public class JsonUtils {
    private JsonUtils() {
    }

    /**
     * 将对象转为json串
     *
     * @param object 对象
     * @return json
     */
    public static String toJSONString(Object object) {
        return JSONUtil.toJsonStr(object);
    }

    /**
     * 将json字符串转为对象
     *
     * @param json  json
     * @param clazz 对象class
     * @param <T>   对象实际类型
     * @return 对象
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSONUtil.toBean(json, clazz);
    }

    /**
     * 解析json为map
     *
     * @param json json
     * @return map
     */
    public static Map<String, Object> parse(String json) {
        return JSONUtil.parseObj(json);
    }

    /**
     * 解析数组json串，为list对象
     *
     * @param json  json串
     * @param clazz list元素class类型
     * @param <T>
     * @return list
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return JSONUtil.toList(JSONUtil.parseArray(json), clazz);
    }
}
