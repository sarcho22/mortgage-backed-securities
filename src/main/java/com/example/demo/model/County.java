package com.example.demo.model;

public class County {
    private String countyCode;
    private String countyName;

    public County() {
    }

    public County(String countyCode, String countyName) {
        this.countyCode = countyCode;
        this.countyName = countyName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @Override
    public String toString() {
        return "County{" +
                "countyCode='" + countyCode + '\'' +
                ", countyName='" + countyName + '\'' +
                '}';
    }
}
