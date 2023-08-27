package com.example.lld.parkinglot.dto;

import com.example.lld.parkinglot.models.ResponseStatus;

public abstract class ResponseDto {

    private ResponseStatus responseStatus;

    public void setResponseStatus(ResponseStatus failure) {
        responseStatus = failure;
    }
}
