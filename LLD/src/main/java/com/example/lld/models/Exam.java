package com.example.lld.models;

import javax.persistence.Entity;

@Entity
public class Exam extends BaseModel{

    private int duration;
    private String name;
}
