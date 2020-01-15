package pl.connectis.restaurant.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Bill {

    private Long id;

    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal tip;

    private List<Dish> dishes;

    private Client client;

    public Bill(Long id,
                LocalDateTime date,
                BigDecimal price,
                BigDecimal tip,
                List<Dish> dishes,
                Client client) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.tip = tip;
        this.dishes = dishes;
        this.client = client;
    }

    public Bill(LocalDateTime date,
                BigDecimal price,
                BigDecimal tip,
                List<Dish> dishes,
                Client client) {
        this.date = date;
        this.price = price;
        this.tip = tip;
        this.dishes = dishes;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public List<Dish> getDishes() {
        if (this.dishes == null) {
            this.dishes = new ArrayList<>();
        }
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill that = (Bill) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bill.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("date=" + date)
                .add("price=" + price)
                .add("tip=" + tip)
                .add("dishes=" + dishes)
                .add("client=" + client)
                .toString();
    }
}
