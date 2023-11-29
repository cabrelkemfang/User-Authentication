package io.growtogether.exeption;

import lombok.Generated;

public class AuthenticationBackendException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Unhandled server exception";
    private final int code;

    public AuthenticationBackendException(String message, int code) {
        super(message);
        this.code = code;
    }

    public AuthenticationBackendException(int code) {
        super(DEFAULT_MESSAGE);
        this.code = code;
    }

    public AuthenticationBackendException(String message, int code, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    @Generated
    public int getCode() {
        return this.code;
    }

}
