package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.ClientHibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientDTO implements Serializable {

    private Long id;

    private String name;

    private String surname;

    private BigDecimal discount;

    public ClientDTO() {
    }

    public ClientDTO(ClientHibernate client) {
        this.id = client.getId();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.discount = client.getDiscount();
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

    public ClientHibernate toDomain() {
        return new ClientHibernate(
                id,
                name,
                surname,
                discount
        );
    }
}
