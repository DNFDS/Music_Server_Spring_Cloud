package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "未知异常，异常原因见日志")
public class ServerException extends RuntimeException {
    public ServerException(Throwable e) {
        super(e);
    }
}
