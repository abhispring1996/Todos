package com.example.ratelimiter.service.impl;

import com.example.ratelimiter.constants.ValidationTypes;
import com.example.ratelimiter.entity.ClientInformationEntity;
import com.example.ratelimiter.entity.RateLimitConfigEntity;
import com.example.ratelimiter.handler.RedisHandler;
import com.example.ratelimiter.models.*;
import com.example.ratelimiter.repository.ClientInformationRepository;
import com.example.ratelimiter.repository.RateLimiterRepository;
import com.example.ratelimiter.service.IRateLimitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class RateLimitServiceImpl implements IRateLimitService {

    private final RedisHandler redisHandler;

    private final RateLimiterRepository rateLimiterRepository;

    private final ClientInformationRepository clientInformationRepository;

    @Autowired
    public RateLimitServiceImpl(RedisHandler redisHandler, RateLimiterRepository rateLimiterRepository, ClientInformationRepository clientInformationRepository) {
        this.redisHandler = redisHandler;
        this.rateLimiterRepository = rateLimiterRepository;
        this.clientInformationRepository = clientInformationRepository;
    }

    @Override
    public ValidateClientResponse isAccessGranted(String clientId) {

        ValidateClientResponse validateClientResponse = new ValidateClientResponse();
        validateClientResponse.setValidationTypes(ValidationTypes.ACCESS_GRANTED);
        validateClientResponse.setMessage(ValidationTypes.ACCESS_GRANTED.getErrorMessage());

        ClientInformationEntity clientInformation = clientInformationRepository.findByClientId(clientId);

        // validate whether client is onboarded
        if (clientInformation == null) {
            validateClientResponse.setValidationTypes(ValidationTypes.ClIENT_NOT_ONBOARDED);
            validateClientResponse.setMessage(ValidationTypes.ClIENT_NOT_ONBOARDED.getErrorMessage());
            return validateClientResponse;
        }

        RateLimitConfigEntity config = clientInformation.getConfig();

        // check if key is already set or not and if limit of number of requests has breached
        if (!redisHandler.setIfAbsent(clientId, config.getNumberOfRequests() - 1, config.getTimeIntervalInSecs())
                && redisHandler.decrementKey(clientId) < 0) {
            validateClientResponse.setValidationTypes(ValidationTypes.TOO_MANY_REQUESTS);
            validateClientResponse.setMessage(ValidationTypes.TOO_MANY_REQUESTS.getErrorMessage());
            return validateClientResponse;
        }

        long quota = clientInformation.getCredits()-1;

        // check if credits have been totally burnt or not
        if(quota<0){
            validateClientResponse.setValidationTypes(ValidationTypes.CREDITS_BURN_EXCEEDED);
            validateClientResponse.setMessage(ValidationTypes.CREDITS_BURN_EXCEEDED.getErrorMessage());
            return validateClientResponse;
        }
        clientInformation.setCredits(quota);
        clientInformationRepository.save(clientInformation);
        validateClientResponse.setAccessGranted(true);
        return validateClientResponse;
    }


    @Override
    @Transactional
    public CreateOrUpdateConfigResponse createOrUpdateRateLimitConfig(CreateOrUpdateConfigRequest createOrUpdateConfigRequest) {

        CreateOrUpdateConfigResponse createOrUpdateConfigResponse = new CreateOrUpdateConfigResponse();
        createOrUpdateConfigResponse.setStatus("F");

        // if no reload config is provided
        if (!createOrUpdateConfigRequest.isDefaultConfigEnabled()
                && createOrUpdateConfigRequest.getNumberOfRequests() == 0 && createOrUpdateConfigRequest.getTimeIntervalInSecs() == 0) {
            createOrUpdateConfigResponse.setMessage("No Configuration Parameter provided");
            return createOrUpdateConfigResponse;
        }


        ClientInformationEntity clientInformation = clientInformationRepository.findByClientId(createOrUpdateConfigRequest.getClientId());

        if (null == clientInformation) {
            clientInformation = new ClientInformationEntity();
            clientInformation.setClientId(createOrUpdateConfigRequest.getClientId());
        }

        int numberOfRequests = 10;
        int timeIntervalInSecs = 60;

        RateLimitConfigEntity rateLimitConfig = rateLimiterRepository.
                findByNumberOfRequestsAndTimeIntervalInSecs(createOrUpdateConfigRequest.getNumberOfRequests(),
                        createOrUpdateConfigRequest.getTimeIntervalInSecs());

        Long configId = 00L; // Default config

        // If default config is not required
        if (!createOrUpdateConfigRequest.isDefaultConfigEnabled()) {

            if (null == rateLimitConfig) {
                rateLimitConfig = new RateLimitConfigEntity();
                rateLimitConfig.setNumberOfRequests(createOrUpdateConfigRequest.getNumberOfRequests());
                rateLimitConfig.setTimeIntervalInSecs(createOrUpdateConfigRequest.getTimeIntervalInSecs());
            }
            rateLimitConfig.setActive(true);
            rateLimitConfig = rateLimiterRepository.save(rateLimitConfig);
            configId = rateLimitConfig.getId();
            numberOfRequests = rateLimitConfig.getNumberOfRequests();
            timeIntervalInSecs = rateLimitConfig.getTimeIntervalInSecs();
        }

        clientInformation.setCredits(clientInformation.getCredits()+ createOrUpdateConfigRequest.getCredits());
        clientInformation.setConfig(rateLimitConfig);
        clientInformationRepository.save(clientInformation);

        redisHandler.set(createOrUpdateConfigRequest.getClientId(), numberOfRequests, timeIntervalInSecs);
        createOrUpdateConfigResponse.setReferenceId(configId);
        createOrUpdateConfigResponse.setStatus("S");
        createOrUpdateConfigResponse.setMessage("Rate Limit Config Reloaded successfully");
        return createOrUpdateConfigResponse;
    }

    @Override
    public ActiveConfigResponse fetchActiveConfig() {

        List<RateLimitConfigEntity> rateLimitConfigInfo = rateLimiterRepository.findByIsActiveTrue();

        ActiveConfigResponse activeConfigResponse = new ActiveConfigResponse();
        List<RateLimitConfigInfo> rateLimitConfigInfoList = new ArrayList<>();

        for (RateLimitConfigEntity rateLimitConfigEntity : rateLimitConfigInfo) {

            RateLimitConfigInfo rateLimitConfig = new RateLimitConfigInfo();
            rateLimitConfig.setConfigId(rateLimitConfigEntity.getId());
            rateLimitConfig.setLimitPerSec(rateLimitConfigEntity.getTimeIntervalInSecs());
            rateLimitConfig.setNumberOfRequests(rateLimitConfigEntity.getNumberOfRequests());
            rateLimitConfigInfoList.add(rateLimitConfig);
        }
        activeConfigResponse.setRateLimitConfigInfoList(rateLimitConfigInfoList);

        return activeConfigResponse;
    }

    @Override
    public CreditsRefilResponse refillCredits(CreditsRefillRequest creditsRefillRequest) {

        CreditsRefilResponse creditsRefilResponse = new CreditsRefilResponse();
        creditsRefilResponse.setStatus("S");
        creditsRefilResponse.setMessage("Credits Refilled SuccessFully");

        ClientInformationEntity clientInformation = clientInformationRepository.findByClientId(creditsRefillRequest.getClientId());

        if(null==clientInformation){
            creditsRefilResponse.setMessage(ValidationTypes.ClIENT_NOT_ONBOARDED.getErrorMessage());
            creditsRefilResponse.setMessage("F");
        }

        clientInformation.setCredits(clientInformation.getCredits()+creditsRefillRequest.getCredits());

        clientInformationRepository.save(clientInformation);

        return creditsRefilResponse;
    }
}
