
package com.magale.roberto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class Contact {

    private String phone;
    private String formattedPhone;

    public Contact(@JsonProperty("phone") String phone, @JsonProperty("formattedPhone") String formattedPhone) {
        this.phone = phone;
        this.formattedPhone = formattedPhone;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("formattedPhone")
    public String getFormattedPhone() {
        return formattedPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phone, contact.phone) &&
                Objects.equals(formattedPhone, contact.formattedPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, formattedPhone);
    }
}
