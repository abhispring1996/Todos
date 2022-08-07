package com.example.ratelimiter;

import com.example.ratelimiter.models.*;
import com.example.ratelimiter.service.IRateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/validate/{clientId}")
    public ResponseEntity<Object> isAccessGranted(@PathVariable(name = "clientId") String clientId){

        int status = HttpStatus.TOO_MANY_REQUESTS.value();

        if(rateLimitServiceImpl.isAccessGranted(clientId)){
            status = HttpStatus.OK.value();
        }

        return ResponseEntity.status(status).build();

    }


    /**
     * To reload rate limit configs
     *
     * @param updateConfigRequest
     * @return
     */
    @PostMapping(path = "/updateConfigs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateConfigResponse> updateConfigs(@RequestBody @Validated UpdateConfigRequest updateConfigRequest) {

        UpdateConfigResponse updateConfigResponse = rateLimitServiceImpl.createOrUpdateRateLimitConfig(updateConfigRequest);

        return ResponseEntity.ok().body(updateConfigResponse);
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

}
