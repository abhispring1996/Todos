package com.example.lld.bookmyshow.models;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
public class Show extends BaseModel{

    // show : movue
    // 1    : 1
    // m    : 1
    @ManyToOne
    Movie movie;

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    @ManyToOne
    Audi audi;

    @OneToMany(mappedBy = "show")
    List<ShowSeat> showSeatList;

    @OneToMany
    private List<ShowSeatType> showSeatTypes;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ShowFeature> showFeatures;

}
