package com.example.lld.bookmyshow.models;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
public class Movie extends BaseModel{

    @OneToMany
    private List<Show> showList;

    @ManyToMany
    private List<Actor> actors;

}
