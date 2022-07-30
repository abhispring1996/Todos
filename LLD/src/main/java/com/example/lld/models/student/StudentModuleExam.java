package com.example.lld.models.student;

import com.example.lld.models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StudentModuleExam extends BaseModel {

    @ManyToOne
    private Student student;
    @ManyToOne
    private ModuleExam moduleExam;
}
