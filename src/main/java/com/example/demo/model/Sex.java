package com.mkyong.jdbc.model;

public class Sex {
    private Integer sex;
    private String sexName;

    public Sex() {
    }

    public Sex(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "sex=" + sex +
                ", sexName='" + sexName + '\'' +
                '}';
    }
}
