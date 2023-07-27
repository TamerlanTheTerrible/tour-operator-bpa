package me.timur.touroperatorbpa.exception;

public enum ResponseCode {
    OK(0),

    INTERNAL_SERVER_ERROR(-100)
    ;

    private final int code;
    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
