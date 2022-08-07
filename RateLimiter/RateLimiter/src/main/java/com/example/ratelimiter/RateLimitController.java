package com.example.ratelimiter;

import com.example.ratelimiter.models.*;
import com.example.ratelimiter.service.IRateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/rateLimit")
public class RateLimitController {

    private final IRateLimitService rateLimitServiceImpl;

    @Autowired
    public RateLimitController(IRateLimitService rateLimitService) {
        this.rateLimitServiceImpl = rateLimitService;
    }


    @GetMapping(path = "/validate/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidateClientResponse> isAccessGranted(@PathVariable(name = "clientId") String clientId) {

        ValidateClientResponse validateClientResponse = rateLimitServiceImpl.isAccessGranted(clientId);

        return ResponseEntity.status(validateClientResponse.getValidationTypes().getHttpStatus()).body(validateClientResponse);

    }


    /**
     * To reload rate limit configs
     *
     * @param createOrUpdateConfigRequest
     * @return
     */
    @PostMapping(path = "/updateConfigs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrUpdateConfigResponse> updateConfigs(@RequestBody
                                                                      @Validated CreateOrUpdateConfigRequest createOrUpdateConfigRequest) {

        CreateOrUpdateConfigResponse createOrUpdateConfigResponse = rateLimitServiceImpl.createOrUpdateRateLimitConfig(createOrUpdateConfigRequest);

        return ResponseEntity.ok().body(createOrUpdateConfigResponse);
    }

    /**
     * To fetch active rate limit config
     *
     * @return
     */
    @GetMapping(path = "/fetchActiveConfigs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActiveConfigResponse> fetchActiveConfigs() {

        ActiveConfigResponse activeConfigResponse = rateLimitServiceImpl.fetchActiveConfig();

        return ResponseEntity.ok().body(activeConfigResponse);
    }

    /**
     * To refill credits for a client
     *
     * @param creditsRefillRequest
     * @return
     */
    @GetMapping(path = "/refillCredits", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditsRefilResponse> refillCredits(@RequestBody CreditsRefillRequest creditsRefillRequest) {

        CreditsRefilResponse creditsRefilResponse = rateLimitServiceImpl.refillCredits(creditsRefillRequest);

        return ResponseEntity.ok().body(creditsRefilResponse);
    }

}
