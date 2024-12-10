package com.example.demo.model;

public class OwnerOccupancy {
    private Integer ownerOccupancy;
    private String ownerOccupancyName;

    public OwnerOccupancy() {
    }

    public OwnerOccupancy(Integer ownerOccupancy, String ownerOccupancyName) {
        this.ownerOccupancy = ownerOccupancy;
        this.ownerOccupancyName = ownerOccupancyName;
    }

    public Integer getOwnerOccupancy() {
        return ownerOccupancy;
    }

    public void setOwnerOccupancy(Integer ownerOccupancy) {
        this.ownerOccupancy = ownerOccupancy;
    }

    public String getOwnerOccupancyName() {
        return ownerOccupancyName;
    }

    public void setOwnerOccupancyName(String ownerOccupancyName) {
        this.ownerOccupancyName = ownerOccupancyName;
    }

    @Override
    public String toString() {
        return "OwnerOccupancy{" +
                "ownerOccupancy=" + ownerOccupancy +
                ", ownerOccupancyName='" + ownerOccupancyName + '\'' +
                '}';
    }
}
