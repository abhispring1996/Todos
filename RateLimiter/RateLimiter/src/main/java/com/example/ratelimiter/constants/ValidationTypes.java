package com.example.ratelimiter.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ValidationTypes {

    ACCESS_GRANTED("Access has been granted.", HttpStatus.OK.value()),
    TOO_MANY_REQUESTS("Too many Requests within defined rate limit.", HttpStatus.TOO_MANY_REQUESTS.value()),
    CREDITS_BURN_EXCEEDED("Credits Empty.They need to be refilled.", HttpStatus.UPGRADE_REQUIRED.value()),
    ClIENT_NOT_ONBOARDED("Please onboard client.",HttpStatus.BAD_REQUEST.value());


    private String errorMessage;
    private int httpStatus;

}
