package com.example.demo.model;

public class LienStatus {
    private Integer lienStatus;
    private String lienStatusName;

    public LienStatus() {
    }

    public LienStatus(Integer lienStatus, String lienStatusName) {
        this.lienStatus = lienStatus;
        this.lienStatusName = lienStatusName;
    }

    public Integer getLienStatus() {
        return lienStatus;
    }

    public void setLienStatus(Integer lienStatus) {
        this.lienStatus = lienStatus;
    }

    public String getLienStatusName() {
        return lienStatusName;
    }

    public void setLienStatusName(String lienStatusName) {
        this.lienStatusName = lienStatusName;
    }

    @Override
    public String toString() {
        return "LienStatus{" +
                "lienStatus=" + lienStatus +
                ", lienStatusName='" + lienStatusName + '\'' +
                '}';
    }
}
