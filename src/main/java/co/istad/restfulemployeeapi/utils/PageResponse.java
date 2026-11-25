package co.istad.restfulemployeeapi.utils;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResponse<T>(

        List<T> content,

        long pageNumber,

        long size,

        long totalElements,

        long totalPages

) {
}
