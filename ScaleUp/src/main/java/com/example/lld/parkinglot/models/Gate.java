package com.example.lld.parkinglot.models;

import lombok.Data;

@Data
public abstract class Gate extends BaseModel{

    private int number;
    private GateType gateType;
    private Operator operator;

    public Gate(GateType gateType){
        this.gateType=gateType;
    }

}
