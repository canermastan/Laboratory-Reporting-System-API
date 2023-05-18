package com.canermastan.laboratoryreportingsystemapi.repository;

import com.canermastan.laboratoryreportingsystemapi.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);

    @Query("SELECT r FROM Report r WHERE r.patientFirstName LIKE %?1% OR r.patientLastName LIKE %?1%")
    List<Report> findAllByPatientFirstNameOrLastName(String name);

    @Query("SELECT r FROM Report r WHERE r.user.firstName LIKE %?1% OR r.user.lastName LIKE %?1%")
    List<Report> findAllByLaboratoryTechnicianFirstNameOrLastName(String name); // LaboratoryTechnician = User

    List<Report> findAllByPatientIdentityNumber(String identityNumber);
    List<Report> findAllByReportDate(LocalDate reportDate);
}
