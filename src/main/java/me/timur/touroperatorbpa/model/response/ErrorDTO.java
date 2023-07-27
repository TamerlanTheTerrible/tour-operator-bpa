package me.timur.touroperatorbpa.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.exception.BaseException;
import me.timur.touroperatorbpa.exception.ResponseCode;

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
        if(e instanceof BaseException) {
            this.code = ((BaseException) e).getErrorConstant().code();
            this.message = e.getMessage();
        } else {
            this.code = ResponseCode.INTERNAL_SERVER_ERROR.code();
            this.message = "INTERNAL_SERVER_ERROR";
        }
    }
}
