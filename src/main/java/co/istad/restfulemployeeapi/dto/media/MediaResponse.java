package co.istad.restfulemployeeapi.dto.media;

import lombok.Builder;

@Builder
public record MediaResponse(
        String fileName,

        String contentType,

        long size,

        String url
) {
}
