package com.hms.repository;

import com.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Transactional
    void deleteByCountryId(Long countryId);

    @Transactional
    void deleteByStateId(Long stateId);

    @Transactional
    void deleteByLocationId(Long locationId);

}


