package com.example.ratelimiter.models;

import lombok.Data;

@Data
public class RateLimitConfigInfo {

    private Long configId;

    private int numberOfRequests;

    private int limitPerSec;

}
