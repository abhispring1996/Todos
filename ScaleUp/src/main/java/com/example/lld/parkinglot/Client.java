package com.example.lld.parkinglot;

import com.example.lld.parkinglot.controller.ParkingLotController;
import com.example.lld.parkinglot.dto.CreateParkingLotRequestDto;
import com.example.lld.parkinglot.repository.ParkingLotRepository;
import com.example.lld.parkinglot.service.ParkingLotService;

public class Client {

    public static void main(String[] args) {

        ObjectRegistry.put("parkingLotRepository",new ParkingLotRepository());
        ObjectRegistry.put("parkingLotService",
                new ParkingLotService((ParkingLotRepository) ObjectRegistry.get("parkingLotRepository")));
        ObjectRegistry.put("parkingLotController",
                new ParkingLotController((ParkingLotService) ObjectRegistry.get("parkingLotService")));

        ParkingLotController parkingLotController = (ParkingLotController) ObjectRegistry.get("parkingLotController");

        CreateParkingLotRequestDto requestDto = new CreateParkingLotRequestDto();
        requestDto.setAddress("Amritsar");
        requestDto.setNoOfFloors(5);

        System.out.println(parkingLotController.createParkingLot(requestDto));

    }
}
