package com.example.ratelimiter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsRefillRequest {

    @NotBlank
    private String clientId;

    @NonNull
    private Integer credits;
}
