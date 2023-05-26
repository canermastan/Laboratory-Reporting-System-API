package com.canermastan.laboratoryreportingsystemapi.service.image;

import com.canermastan.laboratoryreportingsystemapi.entity.ImageData;
import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.repository.ImageRepository;
import com.canermastan.laboratoryreportingsystemapi.service.report.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{

    public ImageServiceImpl(ImageRepository imageRepository, ReportService reportService) {
        this.imageRepository = imageRepository;
        this.reportService = reportService;
    }

    private final ImageRepository imageRepository;
    private final ReportService reportService;

    @Override
    public ImageData saveReportImage(MultipartFile file, Long reportId) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")) {
                throw new Exception("Hata! Dosya adı geçersiz yol dizisi içeriyor " + fileName);
            }

            ImageData imageData = new ImageData();

            imageData.setName(fileName);
            imageData.setType(file.getContentType());
            imageData.setData(file.getBytes());

            ImageData savedImageData = imageRepository.save(imageData);

            Report report = reportService.findById(reportId);
            report.setReportImage(savedImageData);

            reportService.save(report);

            return savedImageData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ImageData findById(String id) {
        return imageRepository.findById(id);
    }
}
