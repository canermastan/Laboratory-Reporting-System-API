package com.canermastan.laboratoryreportingsystemapi.service.report;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.exception.ResourceNotFoundException;
import com.canermastan.laboratoryreportingsystemapi.repository.ReportRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    private final ReportRepository reportRepository;

    @Override
    @CacheEvict(value = "reports", allEntries = true)
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    @CacheEvict(value = "reports", allEntries = true)
    public Report update(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report findById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id: " + id + " ile eşleşen bir rapor bulunamadı."));
    }

    @Override
    @CacheEvict(value = "reports", allEntries = true)
    public void deleteById(Long id) {

        Report report = findById(id);

        if (report != null){
            reportRepository.deleteById(id);
        }

    }

    @Override
    public Page<Report> findAll(int page) {
        // Her sayfada 20 rapor gösterilecek.
        Pageable pageable = PageRequest.of(page, 20);

        return reportRepository.findAll(pageable);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findAllByPatientFirstNameOrLastName(String name) {
        return reportRepository.findAllByPatientFirstNameOrLastName(name);
    }

    @Override
    public List<Report> findAllByPatientIdentityNumber(String identityNumber) {
        return reportRepository.findAllByPatientIdentityNumber(identityNumber);
    }

    @Override
    public List<Report> findAllByReportDate(LocalDate reportDate) {
        return reportRepository.findAllByReportDate(reportDate);
    }

    @Override
    public List<Report> findAllByLaboratoryTechnicianFirstNameOrLastName(String name) {
        return reportRepository.findAllByLaboratoryTechnicianFirstNameOrLastName(name);
    }
}
