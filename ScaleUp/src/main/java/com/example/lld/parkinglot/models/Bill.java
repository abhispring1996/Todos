package com.example.lld.parkinglot.models;

import java.time.ZonedDateTime;

public class Bill extends BaseModel{

    private Ticket ticket;
    private String amount;
    private ZonedDateTime exitTime;
    private String invoiceNumber;
    private Payment payment;
    private BillStatus billStatus;

}
