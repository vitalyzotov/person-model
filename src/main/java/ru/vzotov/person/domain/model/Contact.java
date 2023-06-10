package ru.vzotov.person.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Contact implements Entity<Contact>, Owned {

    private ContactId contactId;

    private PersonId owner;

    private String firstName;

    private String middleName;

    private String lastName;

    private String displayName;

    /**
     * Phone numbers
     * Email addresses
     */
    private Set<ContactData> data;

    /**
     * Entity versioning
     */
    private long version;

    private OffsetDateTime created;

    private OffsetDateTime updated;

    public Contact(ContactId contactId, PersonId owner, String firstName, String middleName, String lastName, String displayName) {
        this(contactId, owner, firstName, middleName, lastName, displayName, Collections.emptySet());
    }

    public Contact(ContactId contactId, PersonId owner, String firstName, String middleName, String lastName, String displayName, Collection<ContactData> data) {
        Validate.notNull(contactId);
        Validate.notNull(owner);
        Validate.notNull(data);
        this.contactId = contactId;
        this.owner = owner;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.data = new HashSet<>(data);
    }

    public static Contact load(ContactId contactId, PersonId owner,
                               String firstName, String middleName, String lastName, String displayName,
                               Collection<ContactData> data,
                               long version, OffsetDateTime created, OffsetDateTime updated) {
        final Contact result = new Contact(contactId, owner, firstName, middleName, lastName, displayName, data);
        result.version = version;
        result.created = created;
        result.updated = updated;
        return result;
    }

    public ContactId contactId() {
        return contactId;
    }

    @Override
    public PersonId owner() {
        return owner;
    }

    public String firstName() {
        return firstName;
    }

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    public String displayName() {
        return displayName;
    }

    public Set<ContactData> data() {
        return Collections.unmodifiableSet(data);
    }

    public long version() {
        return version;
    }

    public OffsetDateTime created() {
        return created;
    }

    public OffsetDateTime updated() {
        return updated;
    }

    public void update(String firstName, String middleName, String lastName, String displayName, Collection<ContactData> data) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public boolean sameIdentityAs(Contact that) {
        return that != null && Objects.equals(contactId, that.contactId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact that = (Contact) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }

    @Override
    public String toString() {
        return "Contact{" + contactId + '}';
    }

    protected Contact() {
        // for ORM
    }

    protected void onCreate() {
        updated = created = OffsetDateTime.now();
    }

    protected void onUpdate() {
        updated = OffsetDateTime.now();
    }

    private Long id; // surrogate key
}
