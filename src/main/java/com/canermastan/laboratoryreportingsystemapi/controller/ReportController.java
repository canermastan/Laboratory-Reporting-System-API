package com.canermastan.laboratoryreportingsystemapi.controller;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.entity.User;
import com.canermastan.laboratoryreportingsystemapi.entity.dtos.ReportDto;
import com.canermastan.laboratoryreportingsystemapi.service.report.ReportService;
import com.canermastan.laboratoryreportingsystemapi.service.user.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    public ReportController(ReportService reportService, UserService userService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    private final ReportService reportService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Cacheable(value = "reports")
    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> findAll() {
        // Verinin cache'ten gelip gelmediğini kontrol etmek için loglama yapıldı.
        logger.info("ReportController - GET - findAll() -> /api/report/all");

        List<Report> reports = reportService.findAll();
        List<ReportDto> reportDtos = new ArrayList<>();
        reports.forEach((report) -> {
            ReportDto rvm = modelMapper.map(report, ReportDto.class);

            rvm.setLaboratoryTechnicianFirstName(report.getUser().getFirstName());
            rvm.setLaboratoryTechnicianLastName(report.getUser().getLastName());
            rvm.setLaboratoryTechnicianHospitalIdentityNumber(report.getUser().getHospitalIdentityNumber());
            reportDtos.add(rvm);
        });

        return ResponseEntity.ok(reportDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> findById(@PathVariable("id") Long id) {
        Report report = reportService.findById(id);

        ReportDto reportDto = modelMapper.map(report, ReportDto.class);

        reportDto.setLaboratoryTechnicianFirstName(report.getUser().getFirstName());
        reportDto.setLaboratoryTechnicianLastName(report.getUser().getLastName());
        reportDto.setLaboratoryTechnicianHospitalIdentityNumber(report.getUser().getHospitalIdentityNumber());

        return ResponseEntity.ok(reportDto);
    }

    @PostMapping("/save")
    public ResponseEntity<ReportDto> save(@RequestBody @Valid ReportDto reportDto, Authentication authentication) {
        System.out.println(authentication.getName());
        User user = userService.findByEmail(authentication.getName());

        Report report = modelMapper.map(reportDto, Report.class);
        report.setUser(user);
        report.setReportDate(LocalDate.now());

        Report reportDb = reportService.save(report);

        ReportDto reportDbDto = modelMapper.map(reportDb, ReportDto.class);
        reportDbDto.setLaboratoryTechnicianFirstName(reportDb.getUser().getFirstName());
        reportDbDto.setLaboratoryTechnicianLastName(reportDb.getUser().getLastName());
        reportDbDto.setLaboratoryTechnicianHospitalIdentityNumber(reportDb.getUser().getHospitalIdentityNumber());

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

}
