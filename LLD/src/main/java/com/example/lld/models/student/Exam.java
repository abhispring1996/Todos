package com.example.lld.models.student;

import com.example.lld.models.BaseModel;

import javax.persistence.Entity;

@Entity
public class Exam extends BaseModel {

    private int duration;
    private String name;
}
