
package com.magale.roberto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

@JsonInclude(NON_NULL)
@JsonDeserialize(builder = Address.Builder.class)
public class Address {

    private String address;
    private String postalCode;
    private String countryCode;
    private String city;
    private String state;
    private String country;

    private Address(Builder builder) {
        this.address = builder.address;
        this.city = builder.city;
        this.country = builder.country;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.countryCode = builder.countryCode;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(postalCode, address1.postalCode) &&
                Objects.equals(countryCode, address1.countryCode) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(state, address1.state) &&
                Objects.equals(country, address1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, postalCode, countryCode, city, state, country);
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private String address;
        private String postalCode;
        private String countryCode;
        private String city;
        private String state;
        private String country;

        private Builder() {
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        private void validate(Address address) {
            checkArgument(!isNullOrEmpty(address.address), "address cannot be null or empty");
            checkArgument(!isNullOrEmpty(address.city), "city cannot be null or empty");
            checkArgument(!isNullOrEmpty(address.country), "country cannot be null or empty");
            checkArgument(!isNullOrEmpty(address.countryCode), "country code cannot be null or empty");
        }

        public Address build() {
            Address address = new Address(this);
            validate(address);
            return address;
        }
    }
}
