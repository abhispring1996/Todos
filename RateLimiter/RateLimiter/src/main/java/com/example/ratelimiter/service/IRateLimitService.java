package com.example.ratelimiter.service;

import com.example.ratelimiter.models.*;

public interface IRateLimitService {

    ValidateClientResponse isAccessGranted(String clientId);

    CreateOrUpdateConfigResponse createOrUpdateRateLimitConfig(CreateOrUpdateConfigRequest createOrUpdateConfigRequest);

    ActiveConfigResponse fetchActiveConfig();

    CreditsRefilResponse refillCredits(CreditsRefillRequest creditsRefillRequest);
}
