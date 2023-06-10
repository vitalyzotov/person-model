package ru.vzotov.person.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class PersonId implements ValueObject<PersonId> {

    private static final String PERSON_AUTHORITY_PREFIX = "PERSON_";

    private String value;

    public PersonId(String value) {
        Validate.notEmpty(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String authority() {
        return PERSON_AUTHORITY_PREFIX + value;
    }

    public static boolean isValidAuthority(String authority) {
        return authority.startsWith(PERSON_AUTHORITY_PREFIX);
    }

    public static PersonId fromAuthority(String authority) {
        if (!isValidAuthority(authority)) throw new IllegalArgumentException();
        return new PersonId(authority.replace(PERSON_AUTHORITY_PREFIX, ""));
    }

    public static PersonId nextId() {
        return new PersonId(UUID.randomUUID().toString());
    }

    @Override
    public boolean sameValueAs(PersonId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonId that = (PersonId) o;
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

    protected PersonId() {
        // for ORM
    }

}
