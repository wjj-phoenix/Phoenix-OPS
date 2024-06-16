package com.phoenix.devops.exception;

import com.phoenix.devops.lang.CodeMessage;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public class BizException extends BaseException implements Serializable {

    @Serial
    private static final long serialVersionUID = -789021883759549647L;

    /**
     * @param errorMsg 错误消息
     */
    public BizException(CodeMessage errorMsg, Object... params) {
        super(errorMsg, params);
    }

    /**
     * @param errCode 错误code
     * @param msg     错误消息
     */
    public BizException(Integer errCode, String msg) {
        super(errCode, msg);
    }
}
