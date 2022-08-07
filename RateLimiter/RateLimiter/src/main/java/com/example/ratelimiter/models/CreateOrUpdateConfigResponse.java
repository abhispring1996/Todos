package com.example.ratelimiter.models;

import lombok.Data;

@Data
public class CreateOrUpdateConfigResponse {

    private long referenceId;
    private String status;
    private String message;

}
