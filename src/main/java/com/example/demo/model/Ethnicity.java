package com.example.demo.model;

public class Ethnicity {
    private String ethnicity;
    private String ethnicityName;

    public Ethnicity() {
    }

    public Ethnicity(String ethnicity, String ethnicityName) {
        this.ethnicity = ethnicity;
        this.ethnicityName = ethnicityName;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthnicityName() {
        return ethnicityName;
    }

    public void setEthnicityName(String ethnicityName) {
        this.ethnicityName = ethnicityName;
    }

    @Override
    public String toString() {
        return "Ethnicity{" +
                "ethnicity='" + ethnicity + '\'' +
                ", ethnicityName='" + ethnicityName + '\'' +
                '}';
    }
}
