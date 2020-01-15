package pl.connectis.restaurant.domain.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Client {

    private Long id;

    private String name;

    private String surname;

    private BigDecimal discount;


    public Client(Long id,
                  String name,
                  String surname,
                  BigDecimal discount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.discount = discount;
    }

    public Client(String name,
                  String surname,
                  BigDecimal discount) {
        this.name = name;
        this.surname = surname;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client that = (Client) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("discount=" + discount)
                .toString();
    }
}
