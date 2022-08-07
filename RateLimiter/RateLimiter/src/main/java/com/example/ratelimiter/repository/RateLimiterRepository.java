package com.example.ratelimiter.repository;

import com.example.ratelimiter.entity.RateLimitConfigEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateLimiterRepository extends CrudRepository<RateLimitConfigEntity,Long>{

    RateLimitConfigEntity findByNumberOfRequestsAndTimeIntervalInSecs(int noOfRequests,int timeInterval);

    List<RateLimitConfigEntity> findByIsActiveTrue();
}
