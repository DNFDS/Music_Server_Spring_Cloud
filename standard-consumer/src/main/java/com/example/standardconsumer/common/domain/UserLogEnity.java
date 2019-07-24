package com.example.standardconsumer.common.domain;

import java.util.Date;

public class UserLogEnity {
    private String type;

    private String classAndMethod;

    private String userId;

    private String ResMsg;

    private String ReqMsg;

    private String addDate;

    private String addTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassAndMethod() {
        return classAndMethod;
    }

    public void setClassAndMethod(String classAndMethod) {
        this.classAndMethod = classAndMethod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResMsg() {
        return ResMsg;
    }

    public void setResMsg(String resMsg) {
        ResMsg = resMsg;
    }

    public String getReqMsg() {
        return ReqMsg;
    }

    public void setReqMsg(String reqMsg) {
        ReqMsg = reqMsg;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}