package me.timur.touroperatorbpa.model.enums;

public enum ResponseCode {
    OK(0),
    // unexpected internal errors
    INTERNAL_SERVER_ERROR(-100),
    // client request errors
    RESOURCE_NOT_FOUND(-201),
    BAD_REQUEST(-202),
    FORBIDDEN_RESOURCE(-203),
    // authentication errors
    AUTHENTICATION_ERROR(-301),
    ;


    private final int code;
    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
