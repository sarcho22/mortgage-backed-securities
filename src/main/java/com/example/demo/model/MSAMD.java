package com.example.demo.model;

public class MSAMD {
    private String msamd;
    private String msamdName;

    public MSAMD() {
    }

    public MSAMD(String msamd, String msamdName) {
        this.msamd = msamd;
        this.msamdName = msamdName;
    }

    public String getMsamd() {
        return msamd;
    }

    public void setMsamd(String msamd) {
        this.msamd = msamd;
    }

    public String getMsamdName() {
        return msamdName;
    }

    public void setMsamdName(String msamdName) {
        this.msamdName = msamdName;
    }

    @Override
    public String toString() {
        return "MSAMD{" +
                "msamd='" + msamd + '\'' +
                ", msamdName='" + msamdName + '\'' +
                '}';
    }
}
