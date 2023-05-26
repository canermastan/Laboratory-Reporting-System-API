package com.canermastan.laboratoryreportingsystemapi.controller.report;

import com.canermastan.laboratoryreportingsystemapi.entity.ImageData;
import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.entity.User;
import com.canermastan.laboratoryreportingsystemapi.entity.dtos.ReportDto;
import com.canermastan.laboratoryreportingsystemapi.service.image.ImageService;
import com.canermastan.laboratoryreportingsystemapi.service.report.ReportService;
import com.canermastan.laboratoryreportingsystemapi.service.user.UserService;
import com.canermastan.laboratoryreportingsystemapi.utils.ReportUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    public ReportController(ReportService reportService, UserService userService, ModelMapper modelMapper, ReportUtil reportUtil, ImageService imageService) {
        this.reportService = reportService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.reportUtil = reportUtil;
        this.imageService = imageService;
    }
    private final ReportService reportService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ReportUtil reportUtil;
    private final ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Cacheable(value = "reports")
    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> findAll() {
        // Verinin cache'ten gelip gelmediğini kontrol etmek için loglama yapıldı.
        logger.info("ReportController - GET - findAll() -> /api/report/all");

        List<Report> reports = reportService.findAll();

        return ResponseEntity.ok(reportUtil.reportListToReportDtoList(reports));
    }

    @Cacheable(value = "reports")
    @GetMapping("/all/{pageNo}")
    public ResponseEntity<ReportPaginationResponse> findAllWithPagination(@PathVariable String pageNo){
        Page<Report> reports = reportService.findAll(Integer.parseInt(pageNo));

        ReportPaginationResponse response = new ReportPaginationResponse(reportUtil.reportListToReportDtoList(reports.toList()), reports.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-by-patient-name")
    public ResponseEntity<List<ReportDto>> findAllByPatientFirstNameOrLastName(@RequestParam String name){
        List<Report> reports = reportService.findAllByPatientFirstNameOrLastName(name);

        return ResponseEntity.ok(reportUtil.reportListToReportDtoList(reports));
    }

    @GetMapping("/all-by-patient-identity-number")
    public ResponseEntity<List<ReportDto>> findAllByPatientIdentityNumber(@RequestParam String id){
        List<Report> reports = reportService.findAllByPatientIdentityNumber(id);

        return ResponseEntity.ok(reportUtil.reportListToReportDtoList(reports));
    }

    @GetMapping("/all-by-report-date")
    public ResponseEntity<List<ReportDto>> findAllByReportDate(@RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date){
        List<Report> reports = reportService.findAllByReportDate(date);

        return ResponseEntity.ok(reportUtil.reportListToReportDtoList(reports));
    }

    @GetMapping("/all-by-laboratory-technician-name")
    public ResponseEntity<List<ReportDto>> findAllByLaboratoryTechnicianFirstNameOrLastName(@RequestParam String name){
        List<Report> reports = reportService.findAllByLaboratoryTechnicianFirstNameOrLastName(name);
        return ResponseEntity.ok(reportUtil.reportListToReportDtoList(reports));
    }

    @GetMapping("/report-no/{reportNo}")
    public ResponseEntity<ReportDto> findByReportNo(@PathVariable("reportNo") String reportNo){
        Report report = reportService.findByReportNo(reportNo);

        return ResponseEntity.ok(reportUtil.reportToReportDto(report));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> findById(@PathVariable("id") Long id) {
        Report report = reportService.findById(id);

        return ResponseEntity.ok(reportUtil.reportToReportDto(report));
    }

    @PostMapping("/save")
    public ResponseEntity<ReportDto> save(@RequestBody @Valid ReportDto reportDto, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());

        Report report = modelMapper.map(reportDto, Report.class);
        report.setUser(user);
        report.setReportDate(LocalDate.now());
        report.setReportNo(reportUtil.generateReportNo());

        Report reportDb = reportService.save(report);

        ReportDto reportDbDto = reportUtil.reportToReportDto(reportDb);

        return ResponseEntity.ok(reportDbDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Report> update(@RequestBody @Valid ReportDto reportDto, @PathVariable("id") Long id) {
        Report reportDb = reportService.findById(id);

        reportDb.setReportNo(reportDto.getReportNo());
        reportDb.setReportDate(reportDto.getReportDate());
        reportDb.setDiagnosisDetail(reportDto.getDiagnosisDetail());
        reportDb.setDiagnosisTitle(reportDto.getDiagnosisTitle());
        reportDb.setPatientFirstName(reportDto.getPatientFirstName());
        reportDb.setPatientLastName(reportDto.getPatientLastName());
        reportDb.setPatientIdentityNumber(reportDto.getPatientIdentityNumber());

        return ResponseEntity.ok(reportService.update(reportDb));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        reportService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/image/upload/{reportId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageData uploadImage(@PathVariable Long reportId, @RequestParam("file") MultipartFile file) {
        return imageService.saveReportImage(file, reportId);
    }

}
