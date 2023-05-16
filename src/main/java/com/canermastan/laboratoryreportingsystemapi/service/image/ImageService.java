package com.canermastan.laboratoryreportingsystemapi.service.image;

import com.canermastan.laboratoryreportingsystemapi.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageData saveImage(MultipartFile file, Long reportId);
    ImageData findById(String id);
}
