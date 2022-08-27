package com.example.lld.parkinglot.models;

import lombok.Data;

import java.util.List;

@Data
public class ParkingLot extends BaseModel{

    private String address;
    private List<ParkingFloor> parkingFloorList;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGatesGates;

}
