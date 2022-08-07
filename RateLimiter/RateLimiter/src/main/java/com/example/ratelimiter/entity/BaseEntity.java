package com.example.ratelimiter.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
public class BaseEntity {

 @CreationTimestamp
 private ZonedDateTime createdTime;

 @UpdateTimestamp
 private ZonedDateTime updatedTime;


}
