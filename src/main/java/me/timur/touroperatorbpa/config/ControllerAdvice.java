package me.timur.touroperatorbpa.config;

import lombok.extern.slf4j.Slf4j;
import me.timur.touroperatorbpa.exception.AuthenticationException;
import me.timur.touroperatorbpa.exception.ClientException;
import me.timur.touroperatorbpa.exception.InternalException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;
import me.timur.touroperatorbpa.model.response.BaseResponse;
import me.timur.touroperatorbpa.model.response.NoopDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Temurbek Ismoilov on 04/08/23.
 */

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ClientException.class)
    public BaseResponse<NoopDTO> handleClientException(ClientException e) {
        log.error(e.getMessage());
        return BaseResponse.error(e.getResponseCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AuthenticationException.class)
    public BaseResponse<NoopDTO> handleAuthenticationException(AuthenticationException e) {
        log.error(e.getMessage());
        return BaseResponse.error(e.getResponseCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = InternalException.class)
    public BaseResponse<NoopDTO> handleInternalException(InternalException e) {
        log.error(e.getMessage(), e);
        return BaseResponse.error(e.getResponseCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<NoopDTO> handleOtherExceptions(Exception e) {
        log.error("Unexpected exception: {}", e.getMessage(), e);
        return BaseResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
