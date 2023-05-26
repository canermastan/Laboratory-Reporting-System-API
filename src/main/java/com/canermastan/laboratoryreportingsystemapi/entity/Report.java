package com.canermastan.laboratoryreportingsystemapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class Report implements Serializable {
    public Report() {
    }

    public Report(Long id, String reportNo, String patientFirstName, String patientLastName, String patientIdentityNumber, String diagnosisTitle, String diagnosisDetail, LocalDate reportDate, ImageData reportImageData, User user) {
        this.id = id;
        this.reportNo = reportNo;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientIdentityNumber = patientIdentityNumber;
        this.diagnosisTitle = diagnosisTitle;
        this.diagnosisDetail = diagnosisDetail;
        this.reportDate = reportDate;
        this.reportImageData = reportImageData;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "report_no")
    private String reportNo;
    @Column(name = "patient_first_name")
    @NotBlank(message = "Hasta Adı zorunlu.")
    private String patientFirstName;
    @Column(name = "patient_last_name")
    @NotBlank(message = "Hasta Soyadı zorunlu.")
    private String patientLastName;
    @Column(name = "patient_identity_number")
    @NotBlank(message = "Hasta TC zorunlu.")
    @Size(min = 11, max = 11, message = "TC Kimlik Numarası 11 haneli olmalıdır.")
    private String patientIdentityNumber;
    @Column(name = "diagnosis_title")
    @NotBlank(message = "Rapor Başlığı zorunlu.")
    private String diagnosisTitle;
    @Column(name = "diagnosis_detail")
    private String diagnosisDetail;
    @Column(name = "report_date")
    private LocalDate reportDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_image_id", referencedColumnName = "id")
    private ImageData reportImageData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
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

    public ImageData getReportImage() {
        return reportImageData;
    }

    public void setReportImage(ImageData reportImageData) {
        this.reportImageData = reportImageData;
    }

    public ImageData getReportImageData() {
        return reportImageData;
    }

    public void setReportImageData(ImageData reportImageData) {
        this.reportImageData = reportImageData;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
