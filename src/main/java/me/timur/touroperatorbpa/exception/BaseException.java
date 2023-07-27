package me.timur.touroperatorbpa.exception;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */

public abstract class BaseException extends RuntimeException {
    private final ResponseCode responseCode;
    public BaseException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getErrorConstant() {
        return responseCode;
    }
}

