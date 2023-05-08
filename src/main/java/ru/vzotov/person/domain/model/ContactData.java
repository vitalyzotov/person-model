package ru.vzotov.person.domain.model;

import org.apache.commons.lang.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;

public class ContactData implements ValueObject<ContactData> {

    private String mimeType;

    private String value;

    private String type;

    public ContactData(String mimeType, String value, String type) {
        Validate.notEmpty(mimeType);
        Validate.notNull(value);
        this.mimeType = mimeType;
        this.value = value;
        this.type = type;
    }

    public String mimeType() {
        return mimeType;
    }

    public String value() {
        return value;
    }

    public String type() {
        return type;
    }

    @Override
    public boolean sameValueAs(ContactData that) {
        return that != null &&
                mimeType.equals(that.mimeType) &&
                value.equals(that.value) &&
                Objects.equals(type, that.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mimeType, value, type);
    }

    protected ContactData() {
        // for Hibernate
    }

    private Long id;
}
