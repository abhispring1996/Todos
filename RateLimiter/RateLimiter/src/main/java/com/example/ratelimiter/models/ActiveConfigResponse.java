package com.example.ratelimiter.models;

import lombok.Data;

import java.util.List;

@Data
public class ActiveConfigResponse {

    List<RateLimitConfigInfo> rateLimitConfigInfoList;
}
