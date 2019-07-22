package com.example.demo.domain;

public class Ban extends BanKey {
    private String bantime;

    private String banreason;

    public String getBantime() {
        return bantime;
    }

    public void setBantime(String bantime) {
        this.bantime = bantime;
    }

    public String getBanreason() {
        return banreason;
    }

    public void setBanreason(String banreason) {
        this.banreason = banreason == null ? null : banreason.trim();
    }
}