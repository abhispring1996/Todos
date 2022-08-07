package com.example.ratelimiter.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rate_limit_configs")
@Getter
@Setter
public class RateLimitConfigEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int numberOfRequests;

    private int timeIntervalInSecs;

    private boolean isActive;

}
