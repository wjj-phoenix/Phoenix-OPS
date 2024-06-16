package com.phoenix.devops.utils.enums;

/**
 * @author wjj-phoenix
 * @param <T> 枚举值类型
 * @since 2024-06-16
 * 最简单的枚举类,即只含value的枚举,实现此接口的枚举类可在如下场景中应用:
 * * <p>1. 使用{@link EnumUtil}中的方法来消除枚举枚举类中重复冗余的代码
 * * 如: {@link EnumUtil#isExist(Class, Object) isExist}、{@link EnumUtil#getEnumByValue(Class, Object) getEnumByValue}等).
 * * <p>2. {@link mayfly.core.validation.annotation.EnumValue @EnumValue}注解,用来校验入参枚举值的正确性.
 */
public interface ValueEnum<T> {

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    T getValue();
}
