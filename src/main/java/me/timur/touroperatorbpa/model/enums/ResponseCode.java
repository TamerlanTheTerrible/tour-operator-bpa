package me.timur.touroperatorbpa.model.enums;

public enum ResponseCode {
    OK(0),

    INTERNAL_SERVER_ERROR(-100),

    RESOURCE_NOT_FOUND(-201),
    BAD_REQUEST(-202),
    ;


    private final int code;
    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
