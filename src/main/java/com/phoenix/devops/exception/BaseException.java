package com.phoenix.devops.exception;

import com.phoenix.devops.lang.CodeMessage;
import lombok.Getter;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
@Getter
public class BaseException extends RuntimeException{
    /**
     * 异常码
     * -- GETTER --
     *  获取错误码
     */
    private Integer errorCode;

    protected BaseException(String msg) {
        super(msg);
    }

    /**
     * @param errorMsg 错误消息
     */
    public BaseException(CodeMessage errorMsg, Object... params) {
        super(params == null ? errorMsg.getMessage() : errorMsg.getMessage(params));
        this.errorCode = errorMsg.getCode();
    }


    /**
     * @param errCode 错误code
     * @param msg     错误消息
     */
    public BaseException(Integer errCode, String msg) {
        super(msg);
        this.errorCode = errCode;
    }

}
