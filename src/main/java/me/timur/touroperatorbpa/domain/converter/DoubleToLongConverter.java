package me.timur.touroperatorbpa.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Converter(autoApply = true)
public class DoubleToLongConverter implements AttributeConverter<Double, Long> {

        @Override
        public Long convertToDatabaseColumn(Double doubleValue) {
            if (doubleValue == null) {
                return null;
            }
            return Math.round(doubleValue * 10);
        }

        @Override
        public Double convertToEntityAttribute(Long longValue) {
            if (longValue == null) {
                return null;
            }
            return longValue / 10.0;
        }
}
