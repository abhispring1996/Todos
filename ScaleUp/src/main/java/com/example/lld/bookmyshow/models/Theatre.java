package com.example.lld.bookmyshow.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Data
public class Theatre extends BaseModel{

    private String name;
    private String location;

    @OneToMany(mappedBy = "theatre",fetch = FetchType.EAGER)
    private List<Audi> audiList;

    @OneToMany
    private List<Show> upcomingShows;

}
