package com.example.lld.parkinglot.models;

import java.time.ZonedDateTime;

public class Payment extends BaseModel{

    private String refNumber;
    private String amount;
    private ZonedDateTime timeOfPayment;
}
