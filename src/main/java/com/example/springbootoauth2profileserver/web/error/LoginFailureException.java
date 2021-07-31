package com.example.springbootoauth2profileserver.web.error;

public final class LoginFailureException extends RuntimeException {

    public LoginFailureException() {
        super();
    }

    public LoginFailureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LoginFailureException(final String message) {
        super(message);
    }

    public LoginFailureException(final Throwable cause) {
        super(cause);
    }
}
