package com.hms.service;

import com.hms.entity.Property;
import com.hms.repository.CountryRepository;
import com.hms.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository, CountryService countryService, StateService stateService, LocationService locationService) {
        this.propertyRepository = propertyRepository;
    }


    public Property addProperties(Property property) {

        Property save = propertyRepository.save(property);

        return save;

    }

    @Transactional
    public void deletePropertyById(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    @Transactional
    public void deletePropertiesByCountryId(Long countryId) {
        propertyRepository.deleteByCountryId(countryId);
    }

    @Transactional
    public void deletePropertiesByStateId(Long stateId) {
        propertyRepository.deleteByStateId(stateId);
        System.out.println("Deleted");
    }

    @Transactional
    public void deletePropertiesByLocationId(Long locationId) {
        propertyRepository.deleteByLocationId(locationId);
    }
}
