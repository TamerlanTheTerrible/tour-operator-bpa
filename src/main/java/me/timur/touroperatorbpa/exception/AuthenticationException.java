package me.timur.touroperatorbpa.exception;

import me.timur.touroperatorbpa.model.enums.ResponseCode;

/**
 * Created by Temurbek Ismoilov on 04/08/23.
 */

public class AuthenticationException extends BaseCustomException{
//    public AuthenticationException(ResponseCode responseCode, String message) {
//        super(responseCode, message);
//    }

    public AuthenticationException(String message) {
        super(ResponseCode.AUTHENTICATION_ERROR, message);
    }
}
