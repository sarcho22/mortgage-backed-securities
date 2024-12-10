package com.example.demo.model;

public class DenialCode {
    private String denialCode;
    private String denialName;

    public DenialCode() {
    }

    public DenialCode(String denialCode, String denialName) {
        this.denialCode = denialCode;
        this.denialName = denialName;
    }

    public String getDenialCode() {
        return denialCode;
    }

    public void setDenialCode(String denialCode) {
        this.denialCode = denialCode;
    }

    public String getDenialName() {
        return denialName;
    }

    public void setDenialName(String denialName) {
        this.denialName = denialName;
    }

    @Override
    public String toString() {
        return "DenialCode{" +
                "denialCode='" + denialCode + '\'' +
                ", denialName='" + denialName + '\'' +
                '}';
    }
}
