package com.magale.roberto.repository;

import com.magale.roberto.exceptions.ListingNotFoundException;
import com.magale.roberto.model.PropertyListing;
import org.junit.Test;

import static com.magale.roberto.PropertyListingDataUtil.TEST_ID;
import static com.magale.roberto.PropertyListingDataUtil.createTestPropertyListing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InMemoryListingRepositoryTest {

    private InMemoryListingRepository repository = new InMemoryListingRepository();

    @Test
    public void whenKeyIsNullThenGetThrowsAnException() {
        assertThatThrownBy(() -> repository.get(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("propertyId cannot be null");
    }

    @Test
    public void whenKeyIsNullThenUpdateThrowsAnException() {
        PropertyListing toUpdate = createTestPropertyListing("address");
        assertThatThrownBy(() -> repository.update(null, toUpdate))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("propertyId cannot be null");
    }

    @Test
    public void whenListingIsNullThenUpdateThrowsAnException() {
        assertThatThrownBy(() -> repository.update("key", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("PropertyListing cannot be null");
    }

    @Test
    public void whenKeyIsNullThenDeleteThrowsAnException() {
        assertThatThrownBy(() -> repository.delete(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("propertyId cannot be null");
    }

    @Test
    public void whenPassedKeyDoesNotCorrespondToAnyListingThenDeleteThrowAnException() {
        assertThatThrownBy(() -> repository.delete("not_existing"))
                .isInstanceOf(ListingNotFoundException.class)
                .hasMessage("Property with Id not_existing does not exist");
    }

    @Test
    public void whenPassedKeyDoesNotCorrespondToAnyListingThenGetThrowAnException() {
        assertThatThrownBy(() -> repository.get("not_existing"))
                .isInstanceOf(ListingNotFoundException.class)
                .hasMessage("Property with Id not_existing does not exist");
    }

    @Test
    public void whenPassedKeyDoesNotCorrespondToAnyListingThenUdateThrowAnException() {
        PropertyListing toUpdate = createTestPropertyListing("address");
        assertThatThrownBy(() -> repository.update("not_existing", toUpdate))
                .isInstanceOf(ListingNotFoundException.class)
                .hasMessage("Property with Id not_existing does not exist");
    }

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

        assertThatThrownBy(() -> repository.get(TEST_ID))
                .isInstanceOf(ListingNotFoundException.class);
    }

    @Test
    public void updateMethodProperlyUpdatesAPropertyListing() {
        PropertyListing testPropertyListing = createTestPropertyListing("address 1");
        repository.add(testPropertyListing);

        PropertyListing otherPropertyListing = createTestPropertyListing("address 2");

        repository.update(TEST_ID, otherPropertyListing);

        assertThat(repository.get(TEST_ID)).isEqualTo(otherPropertyListing);
    }


}