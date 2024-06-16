package com.phoenix.devops.lang;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    
    private Result() {
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param result 获取的数据
     */
    public static <T> Result<T> success(T result) {
        return new Result<>(RespCode.SUCCESS.getCode(), RespCode.SUCCESS.getMessage(), result);
    }

    /**
     * 操作结果对象简单工厂
     *
     * @param code 错误码对象
     * @param <T>  实体类型
     * @return result
     */
    public static <T> Result<T> of(Integer code, String msg) {
        return new Result<T>(code, msg);
    }

    /**
     * 操作结果对象简单工厂 <br/>
     * 可扩展结果操作码和操作结果消息(即实现{@link CodeMessage}接口的枚举类即可)
     *
     * @param resultEnum 结果枚举类
     * @return 结果对象
     */
    public static <T> Result<T> of(CodeMessage resultEnum, Object... params) {
        return new Result<T>(resultEnum.getCode(), params == null ? resultEnum.getMessage() : resultEnum.getMessage(params));
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <Null> Result<Null> failed(String message) {
        return new Result<>(RespCode.CUSTOM_EXCEPTION.getCode(), message, null);
    }

    public static <Null> Result<Null> failed(RespCode resp) {
        return new Result<>(resp.getCode(), resp.getMessage(), null);
    }

    public static <Null> Result<Null> failed(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
