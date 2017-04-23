package com.magale.roberto.repository;

import com.magale.roberto.exceptions.ListingNotFoundException;
import com.magale.roberto.model.PropertyListing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

@org.springframework.stereotype.Repository
public class InMemoryListingRepository implements Repository<String, PropertyListing> {

    private final Map<String, PropertyListing> propertiesMap = new ConcurrentHashMap<>();

    @Override
    public PropertyListing get(String propertyId) {
        checkNotNull(propertyId, "propertyId cannot be null");
        PropertyListing propertyListing = propertiesMap.get(propertyId);
        if (isNull(propertyListing)) {
            throw new ListingNotFoundException(propertyId);
        }

        return propertyListing;
    }

    @Override
    public void add(PropertyListing propertyListing) {
        validateProperty(propertyListing);
        propertiesMap.put(propertyListing.getListing().getId(), propertyListing);
    }

    @Override
    public void delete(String propertyId) {
        checkNotNull(propertyId, "propertyId cannot be null");
        if (propertiesMap.remove(propertyId) == null) {
            throw new ListingNotFoundException(propertyId);

        }
    }

    @Override
    public void update(String propertyId, PropertyListing propertyListing) {
        checkNotNull(propertyId, "propertyId cannot be null");
        validateProperty(propertyListing);
        if (propertiesMap.putIfAbsent(propertyId, propertyListing) == null) {
            throw new ListingNotFoundException(propertyId);
        }

    }

    private void validateProperty(PropertyListing propertyListing) {
        checkNotNull(propertyListing, "PropertyListing cannot be null");
        checkNotNull(propertyListing.getListing(), "listing cannot be null");
        checkNotNull(propertyListing.getListing().getId(), "listing id cannot be null");
    }
}
