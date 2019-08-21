package com.example.demo.mq;

import java.io.Serializable;

public class MessageModel implements Serializable
{
    private Long id;
    private Integer status;
    private String detail;
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

}