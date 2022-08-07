package com.example.ratelimiter.service;

import com.example.ratelimiter.models.*;

public interface IRateLimitService {

    boolean isAccessGranted(String clientId);

    UpdateConfigResponse createOrUpdateRateLimitConfig(UpdateConfigRequest updateConfigRequest);

    ActiveConfigResponse fetchActiveConfig();
}
