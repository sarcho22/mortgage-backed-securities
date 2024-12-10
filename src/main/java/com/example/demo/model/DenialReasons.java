package com.example.demo.model;

public class DenialReasons {
    private Integer denialNum;
    private Integer applicationId;
    private String denialCode;

    public DenialReasons() {
    }

    public DenialReasons(Integer denialNum, Integer applicationId, String denialCode) {
        this.denialNum = denialNum;
        this.applicationId = applicationId;
        this.denialCode = denialCode;
    }

    public Integer getDenialNum() {
        return denialNum;
    }

    public void setDenialNum(Integer denialNum) {
        this.denialNum = denialNum;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getDenialCode() {
        return denialCode;
    }

    public void setDenialCode(String denialCode) {
        this.denialCode = denialCode;
    }

    @Override
    public String toString() {
        return "DenialReasons{" +
                "denialNum=" + denialNum +
                ", applicationId=" + applicationId +
                ", denialCode='" + denialCode + '\'' +
                '}';
    }
}
