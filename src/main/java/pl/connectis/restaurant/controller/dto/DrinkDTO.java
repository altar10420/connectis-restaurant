package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.model.Drink;

import java.io.Serializable;
import java.math.BigDecimal;

public class DrinkDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean is_available;

    private BigDecimal portion_ml;

    public DrinkDTO() {
    }

    public DrinkDTO(Drink drink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.is_available = is_available;
        this.portion_ml = portion_ml;
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
        return is_available;
    }

    public void setAvailable(Boolean available) {
        is_available = available;
    }

    public BigDecimal getPortion_ml() {
        return portion_ml;
    }

    public void setPortion_ml(BigDecimal portion_ml) {
        this.portion_ml = portion_ml;
    }

    public Drink toDomain() {
        return new Drink(
                id,
                name,
                description,
                price,
                is_available,
                portion_ml
        );
    }
}
