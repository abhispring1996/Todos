package com.example.lld.parkinglot.service;

import com.example.lld.parkinglot.models.ParkingLot;
import com.example.lld.parkinglot.repository.ParkingLotRepository;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository){
        this.parkingLotRepository=parkingLotRepository;
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot){
        return parkingLotRepository.save(parkingLot);
    }
}
