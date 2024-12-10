package com.example.demo.model;

public class Preapproval {
    private Integer preapproval;
    private String preapprovalName;

    public Preapproval() {
    }

    public Preapproval(Integer preapproval, String preapprovalName) {
        this.preapproval = preapproval;
        this.preapprovalName = preapprovalName;
    }

    public Integer getPreapproval() {
        return preapproval;
    }

    public void setPreapproval(Integer preapproval) {
        this.preapproval = preapproval;
    }

    public String getPreapprovalName() {
        return preapprovalName;
    }

    public void setPreapprovalName(String preapprovalName) {
        this.preapprovalName = preapprovalName;
    }

    @Override
    public String toString() {
        return "Preapproval{" +
                "preapproval=" + preapproval +
                ", preapprovalName='" + preapprovalName + '\'' +
                '}';
    }
}
