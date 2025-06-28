package com.example.e_commerce_rest_api.utils.helper;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ConvertHelper {
    public String toString(Object value) {
        return value == null ? null : value.toString();
    }

    public Integer toInteger(Object value) {
        return value == null ? null : ((Number) value).intValue();
    }

    public Long toLong(Object value) {
        return value == null ? null : ((Number) value).longValue();
    }

    public BigDecimal toBigDecimal(Object value) {
        return value == null ? null : new BigDecimal(value.toString());
    }

    public <T extends Enum<T>> T toEnum(Class<T> enumType, Object value) {
        if (value == null || value.toString().isBlank()) return null;
        try {
            return Enum.valueOf(enumType, value.toString());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public LocalDate toLocalDate(Object value) {
        if (value == null) return null;
        if (value instanceof java.sql.Date sqlDate) {
            return sqlDate.toLocalDate();
        } else if (value instanceof java.util.Date utilDate) {
            return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public LocalDateTime toLocalDateTime(Object value) {
        if (value == null) return null;
        if (value instanceof java.sql.Timestamp sqlDateTime) {
            return sqlDateTime.toLocalDateTime();
        } else if (value instanceof java.util.Date utilDate) {
            return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }
}
