package com.example.demo.model;

public class RaceCode {
    private String raceCode;
    private String raceName;

    public RaceCode() {
    }

    public RaceCode(String raceCode, String raceName) {
        this.raceCode = raceCode;
        this.raceName = raceName;
    }

    public String getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @Override
    public String toString() {
        return "RaceCode{" +
                "raceCode='" + raceCode + '\'' +
                ", raceName='" + raceName + '\'' +
                '}';
    }
}
