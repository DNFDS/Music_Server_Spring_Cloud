package com.example.demo.entity;

import java.util.Date;

public class Buy extends BuyKey {
    private Date buytime;

    private String type;

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
}