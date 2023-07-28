package me.timur.touroperatorbpa.exception;

import lombok.Getter;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */

@Getter
public class OperatorBpaException extends RuntimeException {
    private final String message;
    private final ResponseCode responseCode;

    public OperatorBpaException(ResponseCode responseCode, String message) {
        this.message = message;
        this.responseCode = responseCode;
    }
}

