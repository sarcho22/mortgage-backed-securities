package com.example.demo.model;

public class Location {
    private Integer applicationId;
    private String msamd;
    private String stateCode;
    private String countyCode;
    private String censusTractNumber;
    private Integer population;
    private Double minorityPopulation;
    private Integer hudMedianFamilyIncome;
    private Double tractToMsamdIncome;
    private Integer numberOfOwnerOccupiedUnits;
    private Integer numberOf1To4FamilyUnits;

    public Location() {
    }

    public Location(Integer applicationId, String msamd, String stateCode, String countyCode,
                    String censusTractNumber, Integer population, Double minorityPopulation,
                    Integer hudMedianFamilyIncome, Double tractToMsamdIncome,
                    Integer numberOfOwnerOccupiedUnits, Integer numberOf1To4FamilyUnits) {
        this.applicationId = applicationId;
        this.msamd = msamd;
        this.stateCode = stateCode;
        this.countyCode = countyCode;
        this.censusTractNumber = censusTractNumber;
        this.population = population;
        this.minorityPopulation = minorityPopulation;
        this.hudMedianFamilyIncome = hudMedianFamilyIncome;
        this.tractToMsamdIncome = tractToMsamdIncome;
        this.numberOfOwnerOccupiedUnits = numberOfOwnerOccupiedUnits;
        this.numberOf1To4FamilyUnits = numberOf1To4FamilyUnits;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getMsamd() {
        return msamd;
    }

    public void setMsamd(String msamd) {
        this.msamd = msamd;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCensusTractNumber() {
        return censusTractNumber;
    }

    public void setCensusTractNumber(String censusTractNumber) {
        this.censusTractNumber = censusTractNumber;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getMinorityPopulation() {
        return minorityPopulation;
    }

    public void setMinorityPopulation(Double minorityPopulation) {
        this.minorityPopulation = minorityPopulation;
    }

    public Integer getHudMedianFamilyIncome() {
        return hudMedianFamilyIncome;
    }

    public void setHudMedianFamilyIncome(Integer hudMedianFamilyIncome) {
        this.hudMedianFamilyIncome = hudMedianFamilyIncome;
    }

    public Double getTractToMsamdIncome() {
        return tractToMsamdIncome;
    }

    public void setTractToMsamdIncome(Double tractToMsamdIncome) {
        this.tractToMsamdIncome = tractToMsamdIncome;
    }

    public Integer getNumberOfOwnerOccupiedUnits() {
        return numberOfOwnerOccupiedUnits;
    }

    public void setNumberOfOwnerOccupiedUnits(Integer numberOfOwnerOccupiedUnits) {
        this.numberOfOwnerOccupiedUnits = numberOfOwnerOccupiedUnits;
    }

    public Integer getNumberOf1To4FamilyUnits() {
        return numberOf1To4FamilyUnits;
    }

    public void setNumberOf1To4FamilyUnits(Integer numberOf1To4FamilyUnits) {
        this.numberOf1To4FamilyUnits = numberOf1To4FamilyUnits;
    }

    @Override
    public String toString() {
        return "Location{" +
                "applicationId=" + applicationId +
                ", msamd='" + msamd + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", censusTractNumber='" + censusTractNumber + '\'' +
                ", population=" + population +
                ", minorityPopulation=" + minorityPopulation +
                ", hudMedianFamilyIncome=" + hudMedianFamilyIncome +
                ", tractToMsamdIncome=" + tractToMsamdIncome +
                ", numberOfOwnerOccupiedUnits=" + numberOfOwnerOccupiedUnits +
                ", numberOf1To4FamilyUnits=" + numberOf1To4FamilyUnits +
                '}';
    }
}
