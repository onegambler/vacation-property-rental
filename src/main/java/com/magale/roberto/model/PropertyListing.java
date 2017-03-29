
package com.magale.roberto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(value = NON_NULL)
public class PropertyListing {

    @JsonProperty("listing")
    private Listing listing;

    public PropertyListing( @JsonProperty("listing") Listing listing) {
        this.listing = listing;
    }

    @JsonProperty("listing")
    public Listing getListing() {
        return listing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyListing that = (PropertyListing) o;
        return Objects.equals(listing, that.listing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listing);
    }
}
