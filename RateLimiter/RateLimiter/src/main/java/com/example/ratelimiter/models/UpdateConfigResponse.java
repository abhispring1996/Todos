package com.example.ratelimiter.models;

import lombok.Data;

@Data
public class UpdateConfigResponse {

    private long referenceId;
    private String status;
    private String message;

}
