package com.example.ratelimiter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "client_information")
@Getter
@Setter
public class ClientInformationEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "client_id")
    private String clientId;

    @ManyToOne
    @JoinColumn(name = "config_id",nullable = false)
    private RateLimitConfigEntity config;
}
