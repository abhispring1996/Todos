package com.example.lld.bookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Actor extends  BaseModel{

    private String actorName;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movieList;
}
