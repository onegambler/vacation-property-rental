package com.magale.roberto.service;

import com.magale.roberto.exceptions.ListingNotFoundException;
import com.magale.roberto.model.Listing;
import com.magale.roberto.model.PropertyListing;
import com.magale.roberto.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ListingService {

    private final Repository<String, PropertyListing> listingRepository;

    public ListingService(Repository<String, PropertyListing> listingRepository) {
        this.listingRepository = listingRepository;
    }

    /**
     * Returns a property list based on the passed id. A {@link ListingNotFoundException}
     * is thrown if PropertyListing does not exist.
     *
     * @param id id of the property list
     * @return the PropertyList object corresponding to the passed id.
     */
    public PropertyListing getListing(String id) {
        return listingRepository.get(id);
    }

    /**
     * Add a PropertyListing to the database. It will set a random UUID as ID.
     *
     * @param propertyListing property listing to store. Cannot be null.
     * @return the stored property listing.
     */
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

    /**
     * Updates an existing property listing, based on the passed id. Both id an property listing cannot be null.
     *
     * @param id              the id of the property listing to update
     * @param propertyListing the property listing to update
     * @return the updated property listing
     */
    public PropertyListing updateListing(String id, PropertyListing propertyListing) {
        checkNotNull(propertyListing, "propertyListing cannot be null");
        this.listingRepository.update(id, propertyListing);
        return propertyListing;
    }


    public void deleteListing(String id) {
        checkNotNull(id, "propertyListing cannot be null");
        this.listingRepository.delete(id);
    }
}
