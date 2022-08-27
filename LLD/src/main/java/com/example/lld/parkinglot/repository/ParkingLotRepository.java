package com.example.lld.parkinglot.repository;

import com.example.lld.parkinglot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {

    Map<Long,ParkingLot> parkingLotMap= new HashMap<>();

    long counter = 0L;

    public ParkingLot save(ParkingLot parkingLot){
        counter+=1;
        parkingLot.setId(counter);
        parkingLotMap.put(counter,parkingLot);
        return parkingLot;
    }
}
