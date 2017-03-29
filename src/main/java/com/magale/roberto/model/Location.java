
package com.magale.roberto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class Location {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;

    public Location(@JsonProperty("lng") Double lng, @JsonProperty("lat") Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lat, location.lat) &&
                Objects.equals(lng, location.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
