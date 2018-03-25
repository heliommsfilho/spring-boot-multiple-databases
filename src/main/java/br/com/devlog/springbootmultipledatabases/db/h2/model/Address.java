package br.com.devlog.springbootmultipledatabases.db.h2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Address Mapping.
 *
 * Repository: <a>https://github.com/heliomf-dev/spring-boot-multiple-databases</a>
 * @author Hélio Márcio Filho <My GitHub: <a>https://github.com/heliomf-dev</a>>
 * @version 1.0
 * @since 1.0
 * */
@Entity
@Table(catalog = "H2_DB_TEST", name = "ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = -8239153122610067639L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
