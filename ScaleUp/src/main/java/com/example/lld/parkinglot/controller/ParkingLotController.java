package com.example.lld.parkinglot.controller;

import com.example.lld.parkinglot.dto.CreateParkingLotRequestDto;
import com.example.lld.parkinglot.dto.CreateParkingLotResponseDto;
import com.example.lld.parkinglot.models.ParkingFloor;
import com.example.lld.parkinglot.models.ParkingLot;
import com.example.lld.parkinglot.models.ResponseStatus;
import com.example.lld.parkinglot.service.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    public CreateParkingLotResponseDto createParkingLot(CreateParkingLotRequestDto request){

       CreateParkingLotResponseDto response = new CreateParkingLotResponseDto();

//        if(request.getNoOfFloors()<2){
//            response.setResponseStatus(ResponseStatus.FAILURE);
//            return response;
//        }
//        ParkingLot parkingLot = new ParkingLot();
//        parkingLot.setAddress(request.getAddress());
//        List<ParkingFloor> parkingFloorList = new ArrayList<>();
//
//        for(int i=0;i<request.getNoOfFloors();i++){
//            parkingFloorList.add(new ParkingFloor());
//        }
//        parkingLot.setParkingFloorList(parkingFloorList);
//
//        ParkingLot parkingLotResponse =  parkingLotService.createParkingLot(parkingLot);
//        response.setParkingLot(parkingLotResponse);
//        response.setResponseStatus(ResponseStatus.SUCCESS);
        return response;
    }

    // update address in parking Lot
    // getParkingLot from Map using id
    //update the parkingLot after setting in map

}
