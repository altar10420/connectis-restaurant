package pl.connectis.restaurant.domain.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Dish {

    private Long id;

    private String description;

    private BigDecimal price;

    private Boolean isAvailable;

    private List<Bill> bills;

    public Dish(Long id,
                String description,
                BigDecimal price,
                Boolean isAvailable) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Dish(String description,
                BigDecimal price,
                Boolean isAvailable) {
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
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

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public List<Bill> getBills() {
        if (this.bills == null) {
            this.bills = new ArrayList<>();
        }
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish that = (Dish) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("description='" + description + "'")
                .add("price=" + price)
                .add("isAvailable=" + isAvailable)
                .toString();
    }


}
