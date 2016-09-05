package ch8;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contact")
@NamedQueries({
@NamedQuery(name = "Contact.findAll", query = "select c from Contact c"),
@NamedQuery(name = "Contact.findAllWithDetail", query = "select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h"),
@NamedQuery(name="Contact.findById",  query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id")
})
@SqlResultSetMapping(
        name="contactResult",
        entities=@EntityResult(entityClass=Contact.class))
//left join fetch c.contactTelDetails t left join fetch c.hobbies h
public class Contact implements Serializable {
    private static final long serialVersionUID = -7690179264720851189L;
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private Set<ContactTelDetail> contactTelDetails = new HashSet<>();
    private Set<Hobby> hobbies = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //значение будет сгенерировано во время вставки
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version // говорит что будем использовать оптимистические блокировки
    @Column(name="version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE) //переводит из util.Date -> sql.Date
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }

    public void addContactTelDetail(ContactTelDetail contactTelDetail){
        contactTelDetail.setContact(this);
        getContactTelDetails().add(contactTelDetail);
    }

    public void removeContactTelDetail(ContactTelDetail contactTelDetail){
        getContactTelDetails().remove(contactTelDetail);
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name="contact_id")
        ,inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
