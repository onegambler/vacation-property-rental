package com.magale.roberto.service;

import com.magale.roberto.model.Listing;
import com.magale.roberto.model.PropertyListing;
import com.magale.roberto.repository.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.magale.roberto.PropertyListingDataUtil.createTestPropertyListing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListingServiceTest {

    @Mock
    private Repository<String, PropertyListing> repository;

    private ListingService listingService;

    @Before
    public void setUp() {
        listingService = new ListingService(repository);
    }

    @Test
    public void whenAValidIdIsPassedThenGetListingReturnsCorrespondingListingData() {
        PropertyListing expected = createTestPropertyListing("address");

        when(repository.get("id")).thenReturn(expected);

        PropertyListing result = listingService.getListing("id");
        assertThat(result).isEqualTo(expected);
        verify(repository).get("id");
    }

    @Test
    public void whenAValidIdIsPassedThenDeleteListingRemoveCorrespondingDataFromDb() {
        listingService.deleteListing("id");
        verify(repository).delete("id");
    }


    @Test
    public void whenAValidIdAndListingArePassedThenUpdateListingUpdatesCorrespondingDataFromDb() {
        PropertyListing expected = createTestPropertyListing("address");
        PropertyListing result = listingService.updateListing("id", expected);
        assertThat(result).isEqualTo(expected);
        verify(repository).update("id", expected);
    }

    @Test
    public void whenAValidListingIsPassedThenUpdateListingCreatesData() {
        PropertyListing expected = createTestPropertyListing("address");

        PropertyListing result = listingService.addListing(expected);

        Listing resultListing = result.getListing();
        assertThat(resultListing.getId()).isNotEmpty();
        assertThat(resultListing.getAddress()).isEqualTo(expected.getListing().getAddress());
        assertThat(resultListing.getContact()).isEqualTo(expected.getListing().getContact());
        assertThat(resultListing.getLocation()).isEqualTo(expected.getListing().getLocation());
        assertThat(resultListing.getId()).isNotEmpty();

        verify(repository).add(result);
    }
}