package ru.vzotov.person.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class ContactId implements ValueObject<ContactId> {

    private String value;

    public ContactId(String value) {
        Validate.notEmpty(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ContactId nextId() {
        return new ContactId(UUID.randomUUID().toString());
    }


    @Override
    public boolean sameValueAs(ContactId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactId that = (ContactId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    protected ContactId() {
        // for Hibernate
    }
}
