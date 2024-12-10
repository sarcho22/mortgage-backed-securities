package com.example.demo.model;

public class ApplicantRace {
    private Integer raceNum;
    private Integer applicationId;
    private String raceCode;

    public ApplicantRace() {
    }

    public ApplicantRace(Integer raceNum, Integer applicationId, String raceCode) {
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
        return "ApplicantRace{" +
                "raceNum=" + raceNum +
                ", applicationId=" + applicationId +
                ", raceCode='" + raceCode + '\'' +
                '}';
    }
}
