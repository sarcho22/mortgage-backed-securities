package com.example.demo.model;

public class LoanType {
    private Integer loanType;
    private String loanTypeName;

    public LoanType() {
    }

    public LoanType(Integer loanType, String loanTypeName) {
        this.loanType = loanType;
        this.loanTypeName = loanTypeName;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    @Override
    public String toString() {
        return "LoanType{" +
                "loanType=" + loanType +
                ", loanTypeName='" + loanTypeName + '\'' +
                '}';
    }
}
