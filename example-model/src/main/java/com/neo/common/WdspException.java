package com.neo.common;

/**
 * Wdsp异常
 *
 * @author lewis
 */
public class WdspException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WdspException() {
        super();
    }

    public WdspException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WdspException(String message, Throwable cause) {
        super(message, cause);
    }

    public WdspException(String message) {
        super(message);
    }

    public WdspException(Throwable cause) {
        super(cause);
    }

}
