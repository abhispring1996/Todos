package com.example.lld.parkinglot.dto;

import com.example.lld.parkinglot.models.EntryGate;
import com.example.lld.parkinglot.models.Vehicle;
import lombok.Data;

@Data
public class GenerateTicketRequestDto {

    private Vehicle vehicle;
    private EntryGate entryGate;
    private Long parkingLotId;

}

