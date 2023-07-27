package me.timur.touroperatorbpa.model.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.atmos.dasturxon.core.model.BaseDTO;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NoopDTO {
    public static final NoopDTO INSTANCE = new NoopDTO();
}
