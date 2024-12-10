package com.example.demo.model;

public class State {
    private String stateCode;
    private String stateName;
    private String stateAbbr;

    public State() {
    }

    public State(String stateCode, String stateName, String stateAbbr) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.stateAbbr = stateAbbr;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateCode='" + stateCode + '\'' +
                ", stateName='" + stateName + '\'' +
                ", stateAbbr='" + stateAbbr + '\'' +
                '}';
    }
}
