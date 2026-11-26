package co.istad.restfulemployeeapi.service.impl;

import ch.qos.logback.core.util.StringUtil;
import co.istad.restfulemployeeapi.dto.media.MediaResponse;
import co.istad.restfulemployeeapi.service.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.client-url}")
    private String clientUrl;

    @Value("${media.base-url}")
    private String baseUrl;

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png");

    @Override
    public MediaResponse uploadSingle(MultipartFile file) {
        try {
            if (!ALLOWED_TYPES.contains(file.getContentType())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Only JPEG and PNG are allowed."
                );
            }

//        1. Extract Extension
            String extension = StringUtils.getFilenameExtension(
                    file.getOriginalFilename()
            );
//        2. Generate New Name
            String newName = UUID.randomUUID().toString() + "." + extension;

//        3. Copy to Media Location
            Path uploadPath = Path.of(mediaLocation)
                    .toAbsolutePath()
                    .normalize();

            Path target = uploadPath.resolve(newName);

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return MediaResponse.builder()
                    .fileName(newName)
                    .size(Files.size(target))
                    .contentType(Files.probeContentType(target))
                    .url(
                            baseUrl + clientUrl + "/" + newName
                    )
                    .build();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "E"
            );
        }
    }

    @Override
    public MediaResponse getMediaByName(String name) {

        try {
            Path uploadPath = Path.of(mediaLocation)
                    .toAbsolutePath()
                    .normalize();

            Path target = uploadPath.resolve(name);

            String contentType = Files.probeContentType(target);

            long size = Files.size(target);

            return MediaResponse.builder()
                    .fileName(name)
                    .contentType(contentType)
                    .size(size)
                    .url(
                            baseUrl + clientUrl + "/" + target.getFileName()
                    )
                    .build();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to get media"
            );
        }

    }

}
