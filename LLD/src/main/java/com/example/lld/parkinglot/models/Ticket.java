package com.example.lld.parkinglot.models;

import java.time.ZonedDateTime;

public class Ticket extends BaseModel{

    private ZonedDateTime entryTime;
    private ParkingSpot parkingSpot;
    private Operator generatedBy;
    private Vehicle vehicle;
    private EntryGate entryGate;

}
