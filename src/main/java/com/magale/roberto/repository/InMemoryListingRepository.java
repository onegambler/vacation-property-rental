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
    public PropertyListing get(String id) {
        checkNotNull(id, "propertyId cannot be null");
        PropertyListing propertyListing = propertiesMap.get(id);
        if (isNull(propertyListing)) {
            String message = String.format("Property with Id %s does not exist", id);
            throw new ListingNotFoundException(message);
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
        PropertyListing removedObject = propertiesMap.remove(propertyId);
        if (removedObject == null) {
            String message = String.format("Property with Id %s does not exist", propertyId);
            throw new ListingNotFoundException(message);
        }
    }

    @Override
    public void update(String propertyId, PropertyListing propertyListing) {
        checkNotNull(propertyId, "propertyId cannot be null");
        validateProperty(propertyListing);
        if (!propertiesMap.containsKey(propertyId)) {
            String message = String.format("Property with Id %s does not exist", propertyId);
            throw new ListingNotFoundException(message);
        }
        propertiesMap.put(propertyId, propertyListing);
    }

    private void validateProperty(PropertyListing propertyListing) {
        checkNotNull(propertyListing, "PropertyListing cannot be null");
        checkNotNull(propertyListing.getListing(), "listing cannot be null");
        checkNotNull(propertyListing.getListing().getId(), "listing id cannot be null");
    }
}
