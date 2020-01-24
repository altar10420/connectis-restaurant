package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.ProductHibernate;

import java.io.Serializable;
import java.math.BigInteger;

public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private BigInteger stored_amount;

    public ProductDTO() {
    }

    public ProductDTO(ProductHibernate product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stored_amount = product.getStored_amount();
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

    public BigInteger getStored_amount() {
        return stored_amount;
    }

    public void setStored_amount(BigInteger stored_amount) {
        this.stored_amount = stored_amount;
    }

    public ProductHibernate toDomain() {
        return new ProductHibernate(
                id,
                name,
                stored_amount
        );
    }
}
