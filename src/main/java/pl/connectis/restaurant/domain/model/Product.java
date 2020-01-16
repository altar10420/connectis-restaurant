package pl.connectis.restaurant.domain.model;

import java.math.BigInteger;
import java.util.Objects;

public class Product {

    private Long id;

    private String name;

    private int stored_amount;

    public Product(Long id, String name, int stored_amount) {
        this.id = id;
        this.name = name;
        this.stored_amount = stored_amount;
    }

    public Product(String name, int stored_amount) {
        this.name = name;
        this.stored_amount = stored_amount;
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

    public int getStored_amount() {
        return stored_amount;
    }

    public void setStored_amount(int stored_amount) {
        this.stored_amount = stored_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stored_amount=" + stored_amount +
                '}';
    }
}

