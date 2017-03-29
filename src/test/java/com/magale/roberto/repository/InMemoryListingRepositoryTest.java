package com.magale.roberto.repository;

import com.magale.roberto.model.Address;
import com.magale.roberto.model.Contact;
import com.magale.roberto.model.Listing;
import com.magale.roberto.model.Location;
import com.magale.roberto.model.PropertyListing;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryListingRepositoryTest {

    private static final String TEST_ID = "id";
    private InMemoryListingRepository repository = new InMemoryListingRepository();

    @Test
    public void addMethodProperlyStoresAPropertyListing() {
        PropertyListing testPropertyListing = createTestPropertyListing("address");

        repository.add(testPropertyListing);

        assertThat(repository.get(TEST_ID)).isEqualTo(testPropertyListing);
    }

    @Test
    public void deleteMethodProperlyDeletesAPropertyListing() {
        PropertyListing testPropertyListing = createTestPropertyListing("address");
        repository.add(testPropertyListing);

        repository.delete(TEST_ID);

        assertThat(repository.get(TEST_ID)).isNull();
    }

    @Test
    public void updateMethodProperlyUpdatesAPropertyListing() {
        PropertyListing testPropertyListing = createTestPropertyListing("address 1");
        repository.add(testPropertyListing);

        PropertyListing otherPropertyListing = createTestPropertyListing("address 2");

        repository.update(TEST_ID, otherPropertyListing);

        assertThat(repository.get(TEST_ID)).isEqualTo(otherPropertyListing);
    }

    private PropertyListing createTestPropertyListing(String addressString) {
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