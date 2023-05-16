package com.canermastan.laboratoryreportingsystemapi.service.report;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import com.canermastan.laboratoryreportingsystemapi.exception.ResourceNotFoundException;
import com.canermastan.laboratoryreportingsystemapi.repository.ReportRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
