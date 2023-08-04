package me.timur.touroperatorbpa.exception;

import lombok.Getter;
import me.timur.touroperatorbpa.model.enums.ResponseCode;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */

@Getter
public abstract class BaseCustomException extends RuntimeException {
    protected final String message;
    protected final ResponseCode responseCode;

    public BaseCustomException(ResponseCode responseCode, String message) {
        this.message = message;
        this.responseCode = responseCode;
    }
}

