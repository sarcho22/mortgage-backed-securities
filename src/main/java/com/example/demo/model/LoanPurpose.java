package com.example.demo.model;

public class LoanPurpose {
    private Integer loanPurpose;
    private String loanPurposeName;

    public LoanPurpose() {
    }

    public LoanPurpose(Integer loanPurpose, String loanPurposeName) {
        this.loanPurpose = loanPurpose;
        this.loanPurposeName = loanPurposeName;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLoanPurposeName() {
        return loanPurposeName;
    }

    public void setLoanPurposeName(String loanPurposeName) {
        this.loanPurposeName = loanPurposeName;
    }

    @Override
    public String toString() {
        return "LoanPurpose{" +
                "loanPurpose=" + loanPurpose +
                ", loanPurposeName='" + loanPurposeName + '\'' +
                '}';
    }
}
