package me.timur.touroperatorbpa.exception;

import me.timur.touroperatorbpa.model.enums.ResponseCode;

/**
 * Created by Temurbek Ismoilov on 04/08/23.
 */

public class ClientException extends BaseCustomException{
    public ClientException(ResponseCode responseCode, String message) {
        super(responseCode, message);
    }

    public ClientException(ResponseCode responseCode, String message, Object... args) {
        super(responseCode, message, args);
    }

}
