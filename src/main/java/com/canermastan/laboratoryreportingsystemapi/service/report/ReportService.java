package com.canermastan.laboratoryreportingsystemapi.service.report;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ReportService {
    Report save(Report report);
    Report update(Report report);
    Report findById(Long id);
    void deleteById(Long id);
    List<Report> findAll();
}
