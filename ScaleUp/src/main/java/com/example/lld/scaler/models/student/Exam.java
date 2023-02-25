package com.example.lld.scaler.models.student;

import com.example.lld.scaler.models.BaseModel;

import javax.persistence.Entity;

@Entity
public class Exam extends BaseModel {

    private int duration;
    private String name;
}
