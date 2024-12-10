package com.example.demo.model;

public class Agency {
    private Integer agencyCode;
    private String agencyName;
    private String agencyAbbr;

    public Agency() {
    }

    public Agency(Integer agencyCode, String agencyName, String agencyAbbr) {
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.agencyAbbr = agencyAbbr;
    }

    public Integer getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(Integer agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyAbbr() {
        return agencyAbbr;
    }

    public void setAgencyAbbr(String agencyAbbr) {
        this.agencyAbbr = agencyAbbr;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "agencyCode=" + agencyCode +
                ", agencyName='" + agencyName + '\'' +
                ", agencyAbbr='" + agencyAbbr + '\'' +
                '}';
    }
}
