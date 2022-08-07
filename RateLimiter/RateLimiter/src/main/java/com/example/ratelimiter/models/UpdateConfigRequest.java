package com.example.ratelimiter.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateConfigRequest {

    @NotBlank
    private String clientId;

    private int numberOfRequests;

    private int timeIntervalInSecs;

    private boolean isDefaultConfigEnabled;
}
