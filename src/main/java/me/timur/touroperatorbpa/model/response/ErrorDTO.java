package me.timur.touroperatorbpa.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.exception.BaseCustomException;
import me.timur.touroperatorbpa.model.enums.ResponseCode;

/**
 * Created by Temurbek Ismoilov on 17/05/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorDTO{
    private Integer code;
    private String message;

    public ErrorDTO(Exception e) {
        if(e instanceof BaseCustomException) {
            this.code = ((BaseCustomException) e).getResponseCode().code();
            this.message = e.getMessage();
        } else {
            this.code = ResponseCode.INTERNAL_SERVER_ERROR.code();
            this.message = "INTERNAL_SERVER_ERROR";
        }
    }
}
