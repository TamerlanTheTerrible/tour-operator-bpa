package me.timur.touroperatorbpa.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.touroperatorbpa.model.enums.ResponseCode;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T payload = null;
    private Integer code;
    private String message;

    public static <T > BaseResponse<T> ok(T payload) {
        return BaseResponse.<T>builder()
                .payload(payload)
                .code(ResponseCode.OK.code())
                .message(null)
                .build();
    }

    public static BaseResponse<NoopDTO> ok() {
        return BaseResponse.<NoopDTO>builder()
                .payload(null)
                .code(ResponseCode.OK.code())
                .message(null)
                .build();
    }

    public static BaseResponse<NoopDTO> error(ResponseCode responseCode, String errorMessage) {
        return BaseResponse.<NoopDTO>builder()
                .payload(null)
                .code(responseCode.code())
                .message(errorMessage)
                .build();
    }
}
