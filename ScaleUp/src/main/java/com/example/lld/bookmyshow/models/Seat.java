package com.example.lld.bookmyshow.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Seat extends BaseModel{

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
