package com.example.demo.model;

public class ActionTaken {
    private Integer actionTaken;
    private String actionTakenName;

    public ActionTaken() {
    }

    public ActionTaken(Integer actionTaken, String actionTakenName) {
        this.actionTaken = actionTaken;
        this.actionTakenName = actionTakenName;
    }

    public Integer getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Integer actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getActionTakenName() {
        return actionTakenName;
    }

    public void setActionTakenName(String actionTakenName) {
        this.actionTakenName = actionTakenName;
    }

    @Override
    public String toString() {
        return "ActionTaken{" +
                "actionTaken=" + actionTaken +
                ", actionTakenName='" + actionTakenName + '\'' +
                '}';
    }
}
