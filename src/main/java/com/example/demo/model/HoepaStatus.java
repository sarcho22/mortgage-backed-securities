package com.example.demo.model;

public class HoepaStatus {
    private Integer hoepaStatus;
    private String hoepaStatusName;

    public HoepaStatus() {
    }

    public HoepaStatus(Integer hoepaStatus, String hoepaStatusName) {
        this.hoepaStatus = hoepaStatus;
        this.hoepaStatusName = hoepaStatusName;
    }

    public Integer getHoepaStatus() {
        return hoepaStatus;
    }

    public void setHoepaStatus(Integer hoepaStatus) {
        this.hoepaStatus = hoepaStatus;
    }

    public String getHoepaStatusName() {
        return hoepaStatusName;
    }

    public void setHoepaStatusName(String hoepaStatusName) {
        this.hoepaStatusName = hoepaStatusName;
    }

    @Override
    public String toString() {
        return "HoepaStatus{" +
                "hoepaStatus=" + hoepaStatus +
                ", hoepaStatusName='" + hoepaStatusName + '\'' +
                '}';
    }
}
