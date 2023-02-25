package com.example.lld.bookmyshow.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Audi extends BaseModel{

    //Many to One
    @ManyToOne
    private Theatre theatre;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Seat> seats;

    private int capacity;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AudiFeature> showFeatures;
}
