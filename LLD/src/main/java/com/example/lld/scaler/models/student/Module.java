package com.example.lld.scaler.models.student;

import com.example.lld.scaler.models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Module extends BaseModel {

    private String name;
    @ManyToMany
    private List<Student> enrolledStudents;

    @OneToMany(mappedBy = "module")
    private List<ModuleExam> moduleExams;

//    M : ModuleExam
//    1 : M
//    M  : 1
}
