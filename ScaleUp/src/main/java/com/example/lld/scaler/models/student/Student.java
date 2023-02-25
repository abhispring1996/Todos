package com.example.lld.scaler.models.student;


import com.example.lld.scaler.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Student extends BaseModel {

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Module> enrolledModules;
}
