package com.canermastan.laboratoryreportingsystemapi.controller.report;

import com.canermastan.laboratoryreportingsystemapi.entity.ImageData;
import com.canermastan.laboratoryreportingsystemapi.service.image.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    private final ImageService imageService;

    @PostMapping(value = "/upload/{reportId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageData uploadImage(@PathVariable Long reportId, @RequestParam("file") MultipartFile file) {
        return imageService.saveImage(file, reportId);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileId) {
        ImageData imageData = imageService.findById(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageData.getName() + "\"")
                .body(new ByteArrayResource(imageData.getData()));
    }
}
