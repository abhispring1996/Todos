package com.example.lld.parkinglot.service;

import com.example.lld.parkinglot.models.EntryGate;
import com.example.lld.parkinglot.models.SpotType;
import com.example.lld.parkinglot.models.Ticket;
import com.example.lld.parkinglot.models.Vehicle;
import com.example.lld.parkinglot.repository.TicketRepository;
import com.example.lld.parkinglot.strategies.SpotAssignmentStrategy;

public class TicketService {

    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;

    public TicketService(TicketRepository ticketRepository, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public Ticket generateTicket(Long parkingLotId, Vehicle vehicle, SpotType spotType, EntryGate entryGate){

        // get ParkingLot
        // find spot depending upon the vehicle Type

        return null;
    }
}
