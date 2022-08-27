package com.example.lld.parkinglot.controller;

import com.example.lld.parkinglot.dto.GenerateTicketResponseDto;
import com.example.lld.parkinglot.dto.GenerateTicketRequestDto;
import com.example.lld.parkinglot.models.Ticket;
import com.example.lld.parkinglot.service.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public GenerateTicketResponseDto generateTicket (GenerateTicketRequestDto generateTicketRequestDto){
        return null;
    }

}
