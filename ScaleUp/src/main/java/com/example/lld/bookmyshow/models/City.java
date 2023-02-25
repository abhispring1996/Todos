package com.example.lld.bookmyshow.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Data
public class City extends BaseModel{

    private String name;
    private String pinCode;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Theatre> theatresList;
}
