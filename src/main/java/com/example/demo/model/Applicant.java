package com.example.demo.model;

public class Applicant {
    private Integer applicationId;
    private String applicantEthnicity;
    private Integer applicantSex;
    private String coapplicantEthnicity;
    private Integer coapplicantSex;
    private Integer applicantIncome000s;

    public Applicant() {
    }

    public Applicant(Integer applicationId, String applicantEthnicity, Integer applicantSex,
                     String coapplicantEthnicity, Integer coapplicantSex, Integer applicantIncome000s) {
        this.applicationId = applicationId;
        this.applicantEthnicity = applicantEthnicity;
        this.applicantSex = applicantSex;
        this.coapplicantEthnicity = coapplicantEthnicity;
        this.coapplicantSex = coapplicantSex;
        this.applicantIncome000s = applicantIncome000s;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicantEthnicity() {
        return applicantEthnicity;
    }

    public void setApplicantEthnicity(String applicantEthnicity) {
        this.applicantEthnicity = applicantEthnicity;
    }

    public Integer getApplicantSex() {
        return applicantSex;
    }

    public void setApplicantSex(Integer applicantSex) {
        this.applicantSex = applicantSex;
    }

    public String getCoapplicantEthnicity() {
        return coapplicantEthnicity;
    }

    public void setCoapplicantEthnicity(String coapplicantEthnicity) {
        this.coapplicantEthnicity = coapplicantEthnicity;
    }

    public Integer getCoapplicantSex() {
        return coapplicantSex;
    }

    public void setCoapplicantSex(Integer coapplicantSex) {
        this.coapplicantSex = coapplicantSex;
    }

    public Integer getApplicantIncome000s() {
        return applicantIncome000s;
    }

    public void setApplicantIncome000s(Integer applicantIncome000s) {
        this.applicantIncome000s = applicantIncome000s;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicationId=" + applicationId +
                ", applicantEthnicity='" + applicantEthnicity + '\'' +
                ", applicantSex=" + applicantSex +
                ", coapplicantEthnicity='" + coapplicantEthnicity + '\'' +
                ", coapplicantSex=" + coapplicantSex +
                ", applicantIncome000s=" + applicantIncome000s +
                '}';
    }
}
