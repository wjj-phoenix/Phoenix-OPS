package com.phoenix.devops.utils.enums;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 * 扩展了{@link ValueEnum}接口. 带有枚举值以及枚举名称的枚举接口(可使用{@link EnumUtil}中的方法。<br/>
 * 如 {@link EnumUtil#getNameByValue(NameValueEnum[], Object) getNameByValue})等
 */
public interface NameValueEnum<T> extends ValueEnum<T> {
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    String getName();
}