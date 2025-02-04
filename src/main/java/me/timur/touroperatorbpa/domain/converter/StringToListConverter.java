package me.timur.touroperatorbpa.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

@Converter(autoApply = true)
public class StringToListConverter implements AttributeConverter<List<String>, String> {

        @Override
        public String convertToDatabaseColumn(List<String> strings) {
            if (strings == null || strings.isEmpty()) {
                return "";
            }
            return String.join(",", strings);
        }

        @Override
        public List<String> convertToEntityAttribute(String s) {
            if (s == null || s.isEmpty()) {
                return List.of();
            }
            return List.of(s.split(","));
        }
}
