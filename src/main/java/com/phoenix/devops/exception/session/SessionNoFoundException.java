package com.phoenix.devops.exception.session;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public class SessionNoFoundException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = -4641006794114763898L;

    public SessionNoFoundException(Exception e) {
        super(e);
    }

    public SessionNoFoundException(String msg) {
        super(msg);
    }
}
