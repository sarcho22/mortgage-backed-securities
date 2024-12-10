package com.example.demo.model;

public class Application {
    private Integer applicationId;
    private Integer prelimId;
    private Integer asOfYear;
    private String respondentId;
    private Integer agencyCode;
    private Integer loanType;
    private Integer loanPurpose;
    private Integer ownerOccupancy;
    private Integer loanAmount000s;
    private Integer preapproval;
    private Integer actionTaken;
    private Integer propertyType;
    private Integer purchaserType;
    private Double rateSpread;
    private Integer hoepaStatus;
    private Integer lienStatus;

    public Application() {
    }

    public Application(Integer applicationId, Integer prelimId, Integer asOfYear, String respondentId,
                       Integer agencyCode, Integer loanType, Integer loanPurpose, Integer ownerOccupancy,
                       Integer loanAmount000s, Integer preapproval, Integer actionTaken, Integer propertyType,
                       Integer purchaserType, Double rateSpread, Integer hoepaStatus, Integer lienStatus) {
        this.applicationId = applicationId;
        this.prelimId = prelimId;
        this.asOfYear = asOfYear;
        this.respondentId = respondentId;
        this.agencyCode = agencyCode;
        this.loanType = loanType;
        this.loanPurpose = loanPurpose;
        this.ownerOccupancy = ownerOccupancy;
        this.loanAmount000s = loanAmount000s;
        this.preapproval = preapproval;
        this.actionTaken = actionTaken;
        this.propertyType = propertyType;
        this.purchaserType = purchaserType;
        this.rateSpread = rateSpread;
        this.hoepaStatus = hoepaStatus;
        this.lienStatus = lienStatus;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getPrelimId() {
        return prelimId;
    }

    public void setPrelimId(Integer prelimId) {
        this.prelimId = prelimId;
    }

    public Integer getAsOfYear() {
        return asOfYear;
    }

    public void setAsOfYear(Integer asOfYear) {
        this.asOfYear = asOfYear;
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }

    public Integer getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(Integer agencyCode) {
        this.agencyCode = agencyCode;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Integer getOwnerOccupancy() {
        return ownerOccupancy;
    }

    public void setOwnerOccupancy(Integer ownerOccupancy) {
        this.ownerOccupancy = ownerOccupancy;
    }

    public Integer getLoanAmount000s() {
        return loanAmount000s;
    }

    public void setLoanAmount000s(Integer loanAmount000s) {
        this.loanAmount000s = loanAmount000s;
    }

    public Integer getPreapproval() {
        return preapproval;
    }

    public void setPreapproval(Integer preapproval) {
        this.preapproval = preapproval;
    }

    public Integer getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Integer actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getPurchaserType() {
        return purchaserType;
    }

    public void setPurchaserType(Integer purchaserType) {
        this.purchaserType = purchaserType;
    }

    public Double getRateSpread() {
        return rateSpread;
    }

    public void setRateSpread(Double rateSpread) {
        this.rateSpread = rateSpread;
    }

    public Integer getHoepaStatus() {
        return hoepaStatus;
    }

    public void setHoepaStatus(Integer hoepaStatus) {
        this.hoepaStatus = hoepaStatus;
    }

    public Integer getLienStatus() {
        return lienStatus;
    }

    public void setLienStatus(Integer lienStatus) {
        this.lienStatus = lienStatus;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", prelimId=" + prelimId +
                ", asOfYear=" + asOfYear +
                ", respondentId='" + respondentId + '\'' +
                ", agencyCode=" + agencyCode +
                ", loanType=" + loanType +
                ", loanPurpose=" + loanPurpose +
                ", ownerOccupancy=" + ownerOccupancy +
                ", loanAmount000s=" + loanAmount000s +
                ", preapproval=" + preapproval +
                ", actionTaken=" + actionTaken +
                ", propertyType=" + propertyType +
                ", purchaserType=" + purchaserType +
                ", rateSpread=" + rateSpread +
                ", hoepaStatus=" + hoepaStatus +
                ", lienStatus=" + lienStatus +
                '}';
    }
}
