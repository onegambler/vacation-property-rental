package com.magale.roberto;

import com.magale.roberto.model.Address;
import com.magale.roberto.model.Contact;
import com.magale.roberto.model.Listing;
import com.magale.roberto.model.Location;
import com.magale.roberto.model.PropertyListing;

public class PropertyListingDataUtil {
    public static final String TEST_ID = "id";

    public static PropertyListing createTestPropertyListing(String addressString) {
        Address address = Address.builder()
                .withAddress(addressString)
                .withCity("City")
                .withCountry("Country")
                .withCountryCode("Code")
                .withPostalCode("PostalCode")
                .withState("Stage")
                .build();
        Listing listing = Listing.builder()
                .withAddress(address)
                .withContact(new Contact("01010110", "+12"))
                .withLocation(new Location(123D, 125D))
                .withId(TEST_ID)
                .build();

        return new PropertyListing(listing);
    }
}
