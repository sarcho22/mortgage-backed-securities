package com.example.demo.model;

public class CoapplicantRace {
    private Integer raceNum;
    private Integer applicationId;
    private String raceCode;

    public CoapplicantRace() {
    }

    public CoapplicantRace(Integer raceNum, Integer applicationId, String raceCode) {
        this.raceNum = raceNum;
        this.applicationId = applicationId;
        this.raceCode = raceCode;
    }

    public Integer getRaceNum() {
        return raceNum;
    }

    public void setRaceNum(Integer raceNum) {
        this.raceNum = raceNum;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    @Override
    public String toString() {
        return "CoapplicantRace{" +
                "raceNum=" + raceNum +
                ", applicationId=" + applicationId +
                ", raceCode='" + raceCode + '\'' +
                '}';
    }
}
