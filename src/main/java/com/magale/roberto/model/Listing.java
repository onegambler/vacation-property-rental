
package com.magale.roberto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.google.common.base.Preconditions.checkNotNull;

@JsonInclude(NON_NULL)
@JsonDeserialize(builder = Listing.Builder.class)
public class Listing {

    private String id;
    private Contact contact;
    private Address address;
    private Location location;

    private Listing(Builder builder) {
        this.id = builder.id;
        this.contact = builder.contact;
        this.address = builder.address;
        this.location = builder.location;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(id, listing.id) &&
                Objects.equals(contact, listing.contact) &&
                Objects.equals(address, listing.address) &&
                Objects.equals(location, listing.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact, address, location);
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private String id;
        private Contact contact;
        private Address address;
        private Location location;

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withLocation(Location location) {
            this.location = location;
            return this;
        }

        private void validate(Listing listing) {
            checkNotNull(listing.address, "PropertyListing address cannot be null");
            checkNotNull(listing.contact, "PropertyListing contact cannot be null");
        }

        public Listing build() {
            Listing listing = new Listing(this);
            validate(listing);
            return listing;
        }
    }
}
