package com.example.lld.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ModuleExam extends BaseModel{

    // ME:M
    // 1:1
    // M:1
    @ManyToOne
    private Module module;
    @ManyToOne
    private Exam exam;

    private Date dateOfExam;
}
