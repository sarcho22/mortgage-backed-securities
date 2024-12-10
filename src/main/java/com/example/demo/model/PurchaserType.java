package com.example.demo.model;

public class PurchaserType {
    private Integer purchaserType;
    private String purchaserTypeName;

    public PurchaserType() {
    }

    public PurchaserType(Integer purchaserType, String purchaserTypeName) {
        this.purchaserType = purchaserType;
        this.purchaserTypeName = purchaserTypeName;
    }

    public Integer getPurchaserType() {
        return purchaserType;
    }

    public void setPurchaserType(Integer purchaserType) {
        this.purchaserType = purchaserType;
    }

    public String getPurchaserTypeName() {
        return purchaserTypeName;
    }

    public void setPurchaserTypeName(String purchaserTypeName) {
        this.purchaserTypeName = purchaserTypeName;
    }

    @Override
    public String toString() {
        return "PurchaserType{" +
                "purchaserType=" + purchaserType +
                ", purchaserTypeName='" + purchaserTypeName + '\'' +
                '}';
    }
}
