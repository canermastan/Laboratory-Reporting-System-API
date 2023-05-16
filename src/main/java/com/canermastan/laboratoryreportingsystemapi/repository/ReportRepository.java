package com.canermastan.laboratoryreportingsystemapi.repository;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
