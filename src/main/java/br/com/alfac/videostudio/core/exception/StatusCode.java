package br.com.alfac.videostudio.core.exception;

public enum StatusCode {
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int code;

    StatusCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
