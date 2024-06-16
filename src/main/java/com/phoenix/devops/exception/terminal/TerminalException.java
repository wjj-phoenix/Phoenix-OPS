package com.phoenix.devops.exception.terminal;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public class TerminalException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = -6213665149000064880L;

    public TerminalException() {
        super();
    }

    public TerminalException(String message) {
        super(message);
    }

    public TerminalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalException(Throwable cause) {
        super(cause);
    }

}
