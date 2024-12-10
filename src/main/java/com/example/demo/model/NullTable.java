package com.example.demo.model;

public class NullTable {
    private Integer applicationId;
    private Integer applicationDateIndicator;
    private String sequenceNumber;
    private String editStatusName;
    private String editStatus;

    public NullTable() {
    }

    public NullTable(Integer applicationId, Integer applicationDateIndicator, String sequenceNumber,
                     String editStatusName, String editStatus) {
        this.applicationId = applicationId;
        this.applicationDateIndicator = applicationDateIndicator;
        this.sequenceNumber = sequenceNumber;
        this.editStatusName = editStatusName;
        this.editStatus = editStatus;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getApplicationDateIndicator() {
        return applicationDateIndicator;
    }

    public void setApplicationDateIndicator(Integer applicationDateIndicator) {
        this.applicationDateIndicator = applicationDateIndicator;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getEditStatusName() {
        return editStatusName;
    }

    public void setEditStatusName(String editStatusName) {
        this.editStatusName = editStatusName;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    @Override
    public String toString() {
        return "NullTable{" +
                "applicationId=" + applicationId +
                ", applicationDateIndicator=" + applicationDateIndicator +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", editStatusName='" + editStatusName + '\'' +
                ", editStatus='" + editStatus + '\'' +
                '}';
    }
}
