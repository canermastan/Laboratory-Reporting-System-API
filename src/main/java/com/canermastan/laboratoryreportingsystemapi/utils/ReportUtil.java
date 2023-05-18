package com.canermastan.laboratoryreportingsystemapi.utils;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.entity.dtos.ReportDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportUtil {

    public ReportUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public List<ReportDto> reportListToReportDtoList(List<Report> reports){
        List<ReportDto> reportDtos = new ArrayList<>();

        reports.forEach((report) -> {
            ReportDto rvm = modelMapper.map(report, ReportDto.class);

            rvm.setLaboratoryTechnicianFirstName(report.getUser().getFirstName());
            rvm.setLaboratoryTechnicianLastName(report.getUser().getLastName());
            rvm.setLaboratoryTechnicianHospitalIdentityNumber(report.getUser().getHospitalIdentityNumber());
            reportDtos.add(rvm);
        });

        return reportDtos;
    }

    public ReportDto reportToReportDto(Report report){
        ReportDto reportDto = modelMapper.map(report, ReportDto.class);

        reportDto.setLaboratoryTechnicianFirstName(report.getUser().getFirstName());
        reportDto.setLaboratoryTechnicianLastName(report.getUser().getLastName());
        reportDto.setLaboratoryTechnicianHospitalIdentityNumber(report.getUser().getHospitalIdentityNumber());

        return reportDto;
    }
}
