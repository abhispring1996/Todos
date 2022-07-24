package com.example.lld.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Module extends BaseModel{

    private String name;
    @ManyToMany
    private List<Student> enrolledStudents;
}
