package co.istad.restfulemployeeapi.service;

import co.istad.restfulemployeeapi.dto.media.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaResponse uploadSingle(
            MultipartFile file
    );

    MediaResponse getMediaByName(
        String name
    );
}
