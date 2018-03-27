package org.pf9.pangu.framework.common.exception;

public class PanguException extends Exception {
    public PanguException() {
    }

    public PanguException(String message) {
        super(message);
    }

    public PanguException(String message, Throwable cause) {
        super(message, cause);
    }

    public PanguException(Throwable cause) {
        super(cause);
    }

    public PanguException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
