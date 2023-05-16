package com.canermastan.laboratoryreportingsystemapi.entity.dtos;

import java.time.LocalDate;

public class ReportDto {

    public ReportDto(Long id, String patientFirstName, String patientLastName, String patientIdentityNumber, String diagnosisTitle, String diagnosisDetail, LocalDate reportDate, String reportNo, String reportImageId, String laboratoryTechnicianFirstName, String laboratoryTechnicianLastName, String laboratoryTechnicianHospitalIdentityNumber) {
        this.id = id;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientIdentityNumber = patientIdentityNumber;
        this.diagnosisTitle = diagnosisTitle;
        this.diagnosisDetail = diagnosisDetail;
        this.reportDate = reportDate;
        this.reportNo = reportNo;
        this.reportImageId = reportImageId;
        this.laboratoryTechnicianFirstName = laboratoryTechnicianFirstName;
        this.laboratoryTechnicianLastName = laboratoryTechnicianLastName;
        this.laboratoryTechnicianHospitalIdentityNumber = laboratoryTechnicianHospitalIdentityNumber;
    }

    public ReportDto() {
    }

    Long id;
    String patientFirstName;
    String patientLastName;
    String patientIdentityNumber;
    String diagnosisTitle;
    String diagnosisDetail;
    LocalDate reportDate;
    String reportNo;

    String reportImageId;

    String laboratoryTechnicianFirstName;
    String laboratoryTechnicianLastName;
    String laboratoryTechnicianHospitalIdentityNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientIdentityNumber() {
        return patientIdentityNumber;
    }

    public void setPatientIdentityNumber(String patientIdentityNumber) {
        this.patientIdentityNumber = patientIdentityNumber;
    }

    public String getDiagnosisTitle() {
        return diagnosisTitle;
    }

    public void setDiagnosisTitle(String diagnosisTitle) {
        this.diagnosisTitle = diagnosisTitle;
    }

    public String getDiagnosisDetail() {
        return diagnosisDetail;
    }

    public void setDiagnosisDetail(String diagnosisDetail) {
        this.diagnosisDetail = diagnosisDetail;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getLaboratoryTechnicianFirstName() {
        return laboratoryTechnicianFirstName;
    }

    public void setLaboratoryTechnicianFirstName(String laboratoryTechnicianFirstName) {
        this.laboratoryTechnicianFirstName = laboratoryTechnicianFirstName;
    }

    public String getLaboratoryTechnicianLastName() {
        return laboratoryTechnicianLastName;
    }

    public void setLaboratoryTechnicianLastName(String laboratoryTechnicianLastName) {
        this.laboratoryTechnicianLastName = laboratoryTechnicianLastName;
    }

    public String getLaboratoryTechnicianHospitalIdentityNumber() {
        return laboratoryTechnicianHospitalIdentityNumber;
    }

    public void setLaboratoryTechnicianHospitalIdentityNumber(String laboratoryTechnicianHospitalIdentityNumber) {
        this.laboratoryTechnicianHospitalIdentityNumber = laboratoryTechnicianHospitalIdentityNumber;
    }

    public String getReportImageId() {
        return reportImageId;
    }

    public void setReportImageId(String reportImageId) {
        this.reportImageId = reportImageId;
    }
}
