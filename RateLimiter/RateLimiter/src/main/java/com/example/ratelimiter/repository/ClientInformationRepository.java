package com.example.ratelimiter.repository;

import com.example.ratelimiter.entity.ClientInformationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInformationRepository extends CrudRepository<ClientInformationEntity,Long> {

    ClientInformationEntity findByClientId(String clientId);
}
