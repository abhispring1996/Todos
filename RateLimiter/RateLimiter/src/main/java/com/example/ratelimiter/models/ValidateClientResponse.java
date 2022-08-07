package com.example.ratelimiter.models;

import com.example.ratelimiter.constants.ValidationTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ValidateClientResponse {

    ValidationTypes validationTypes;
    String message;
    boolean isAccessGranted;

}
