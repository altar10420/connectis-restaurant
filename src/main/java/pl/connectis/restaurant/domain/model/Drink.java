package pl.connectis.restaurant.domain.model;

import java.util.Objects;

public class Drink {

    private Long id;

    private String name;

    private String description;

    private double price;

    private Boolean is_available;

    private int portion_ml;

    public Drink(Long id, String name, String description, double price, Boolean is_available, int portion_ml) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.is_available = is_available;
        this.portion_ml = portion_ml;
    }

    public Drink(String name, String description, double price, Boolean is_available, int portion_ml) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.is_available = is_available;
        this.portion_ml = portion_ml;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public int getPortion_ml() {
        return portion_ml;
    }

    public void setPortion_ml(int portion_ml) {
        this.portion_ml = portion_ml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(id, drink.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", is_available=" + is_available +
                ", portion_ml=" + portion_ml +
                '}';
    }
}
