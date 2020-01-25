package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.DishHibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class DishDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isAvailable;

    public DishDTO() {
    }

    public DishDTO(DishHibernate dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
        this.isAvailable = dish.getAvailable();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public DishHibernate toDomain() {
        return new DishHibernate(
                id,
                name,
                description,
                price,
                isAvailable
        );
    }
}
