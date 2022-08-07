package com.example.ratelimiter.service.impl;

import com.example.ratelimiter.entity.ClientInformationEntity;
import com.example.ratelimiter.entity.RateLimitConfigEntity;
import com.example.ratelimiter.handler.RedisHandler;
import com.example.ratelimiter.models.ActiveConfigResponse;
import com.example.ratelimiter.models.RateLimitConfigInfo;
import com.example.ratelimiter.models.UpdateConfigRequest;
import com.example.ratelimiter.models.UpdateConfigResponse;
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
    public boolean isAccessGranted(String clientId) {

        ClientInformationEntity clientInformation = clientInformationRepository.findByClientId(clientId);

        if (clientInformation == null) {
            return false;
        }

        RateLimitConfigEntity config = clientInformation.getConfig();

        if (!redisHandler.setIfAbsent(clientId, config.getNumberOfRequests() - 1, config.getTimeIntervalInSecs())
                && redisHandler.decrementKey(clientId) < 0) {
            return false;
        }
        return true;
    }


    @Override
    @Transactional
    public UpdateConfigResponse createOrUpdateRateLimitConfig(UpdateConfigRequest updateConfigRequest) {

        UpdateConfigResponse updateConfigResponse = new UpdateConfigResponse();
        updateConfigResponse.setStatus("F");

        if (!updateConfigRequest.isDefaultConfigEnabled()
                && updateConfigRequest.getNumberOfRequests() == 0 && updateConfigRequest.getTimeIntervalInSecs() == 0) {
            updateConfigResponse.setMessage("No Configuration Parameter provided");
            return updateConfigResponse;
        }


        ClientInformationEntity clientInformation = clientInformationRepository.findByClientId(updateConfigRequest.getClientId());

        if (null == clientInformation) {
            clientInformation = new ClientInformationEntity();
            clientInformation.setClientId(updateConfigRequest.getClientId());
        }

        int numberOfRequests = 10;
        int timeIntervalInSecs = 60;

        RateLimitConfigEntity rateLimitConfig = rateLimiterRepository.
                findByNumberOfRequestsAndTimeIntervalInSecs(updateConfigRequest.getNumberOfRequests(),
                        updateConfigRequest.getTimeIntervalInSecs());

        Long configId = 00L; // Default config

        // If default config is not required
        if (!updateConfigRequest.isDefaultConfigEnabled()) {

            if (null == rateLimitConfig) {
                rateLimitConfig = new RateLimitConfigEntity();
                rateLimitConfig.setNumberOfRequests(updateConfigRequest.getNumberOfRequests());
                rateLimitConfig.setTimeIntervalInSecs(updateConfigRequest.getTimeIntervalInSecs());
            }
            rateLimitConfig.setActive(true);
            rateLimitConfig = rateLimiterRepository.save(rateLimitConfig);
            configId = rateLimitConfig.getId();
            numberOfRequests = rateLimitConfig.getNumberOfRequests();
            timeIntervalInSecs = rateLimitConfig.getTimeIntervalInSecs();
        }


        clientInformation.setConfig(rateLimitConfig);
        clientInformationRepository.save(clientInformation);

        redisHandler.set(updateConfigRequest.getClientId(), numberOfRequests, timeIntervalInSecs);
        updateConfigResponse.setReferenceId(configId);
        updateConfigResponse.setStatus("S");
        updateConfigResponse.setMessage("Rate Limit Config Reloaded successfully");
        return updateConfigResponse;
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
}
