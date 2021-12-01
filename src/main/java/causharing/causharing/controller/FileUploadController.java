package causharing.causharing.controller;

import causharing.causharing.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    /**
     * multipart/form-data 입력
     */
    @PostMapping("/upload")
    public String uploadImage(@RequestPart MultipartFile file) {
        return fileUploadService.uploadImage(file);

    }

    @DeleteMapping("/delete")
    public String deleleImage(@RequestParam String fileName) {
        return fileUploadService.deleteFileName(fileName);
    };


}
