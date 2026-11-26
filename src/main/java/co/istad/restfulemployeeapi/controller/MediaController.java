package co.istad.restfulemployeeapi.controller;

import co.istad.restfulemployeeapi.dto.media.MediaResponse;
import co.istad.restfulemployeeapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medias")
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/{name}")
    public MediaResponse getMediaByName(@PathVariable String name) {
        return mediaService.getMediaByName(name);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MediaResponse uploadSingle(
            @RequestPart("file")MultipartFile file
            ) {
        return mediaService.uploadSingle(file);
    }

}
