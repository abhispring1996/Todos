package com.example.lld.parkinglot.dto;

import com.example.lld.parkinglot.models.ParkingLot;
import com.example.lld.parkinglot.models.ResponseStatus;
import lombok.Data;

@Data
public class CreateParkingLotRequestDto {

    private String address;
    private int noOfFloors;
    private int noOfEntryGates;
    private int noOfExitGates;

}

