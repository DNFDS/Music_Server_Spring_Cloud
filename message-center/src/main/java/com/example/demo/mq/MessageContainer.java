package com.example.demo.mq;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.demo.mq.AppEventPublisher.AppEvent;


public class MessageContainer
{
    private ConcurrentHashMap<Long, AppEvent> messageContainer;

    public MessageContainer()
    {
        this.messageContainer = new ConcurrentHashMap<>();
    }
    public AppEvent getModel(Long id)
    {
        return this.messageContainer.get(id);
    }

    public void updateMessageStatus(Integer status, Long id)
    {
        synchronized (messageContainer)
        {
            AppEvent event = messageContainer.get(id);
            event.setStatus(status);
        }
    }

    public void addMessage(AppEvent event)
    {
        this.messageContainer.put(Long.valueOf(event.getId()), event);
    }

    public ConcurrentHashMap<Long, AppEvent> getMessageContainer()
    {
        return messageContainer;
    }

    public void setMessageContainer(ConcurrentHashMap<Long, AppEvent> messageContainer)
    {
        this.messageContainer = messageContainer;
    }

}
