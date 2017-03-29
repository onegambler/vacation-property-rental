package com.magale.roberto.service;

import com.magale.roberto.exceptions.ListingNotFoundException;
import com.magale.roberto.model.Listing;
import com.magale.roberto.model.PropertyListing;
import com.magale.roberto.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

@Service
public class ListingService {

    private final Repository<String, PropertyListing> listingRepository;

    public ListingService(Repository<String, PropertyListing> listingRepository) {
        this.listingRepository = listingRepository;
    }

    public PropertyListing getListing(String id) {
        PropertyListing propertyListing = listingRepository.get(id);
        if (isNull(propertyListing)) {
            String message = String.format("Impossible to find listing with id %s", id);
            throw new ListingNotFoundException(message);
        }

        return propertyListing;
    }

    public PropertyListing addListing(PropertyListing propertyListing) {
        checkNotNull(propertyListing, "propertyListing cannot be null");
        String listingID = UUID.randomUUID().toString();
        Listing listing = propertyListing.getListing();
        Listing copyListing = Listing.builder()
                .withAddress(listing.getAddress())
                .withContact(listing.getContact())
                .withLocation(listing.getLocation())
                .withId(listingID)
                .build();

        PropertyListing storedPropertyListing = new PropertyListing(copyListing);
        this.listingRepository.add(storedPropertyListing);
        return storedPropertyListing;
    }

    public PropertyListing updateListing(String id, PropertyListing propertyListing) {
        checkNotNull(propertyListing, "propertyListing cannot be null");
        this.listingRepository.update(id, propertyListing);
        return propertyListing;
    }

    public void deleteListing(String id) {
        checkNotNull(id, "listing id cannot be null");
        this.listingRepository.delete(id);
    }
}
