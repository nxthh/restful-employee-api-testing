package co.istad.restfulemployeeapi.exception;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse<T>(

        Boolean status,

        Integer code,

        String message,

        T errors,

        LocalDateTime timestamp

) {
}
