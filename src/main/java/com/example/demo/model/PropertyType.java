package com.example.demo.model;

public class PropertyType {
    private Integer propertyType;
    private String propertyTypeName;

    public PropertyType() {
    }

    public PropertyType(Integer propertyType, String propertyTypeName) {
        this.propertyType = propertyType;
        this.propertyTypeName = propertyTypeName;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    @Override
    public String toString() {
        return "PropertyType{" +
                "propertyType=" + propertyType +
                ", propertyTypeName='" + propertyTypeName + '\'' +
                '}';
    }
}