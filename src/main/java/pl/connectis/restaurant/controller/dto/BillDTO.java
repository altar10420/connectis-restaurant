package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDTO implements Serializable {

    private Long id;

    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal tip;

    private List<Long> dishes = new ArrayList<>();

    private List<Long> drinks = new ArrayList<>();

    private Long clientId;

    private Long employeeId;

    public BillDTO() {
    }

    public BillDTO(BillHibernate bill) {
        this.id = bill.getId();
        this.date = bill.getDate();
        this.price = bill.getPrice();
        this.tip = bill.getTip();
        this.clientId = bill.getClient().getId();
        this.employeeId = bill.getEmployee().getId();

        for (DishHibernate dish : bill.getDishes()) {
            this.dishes.add(dish.getId());
        }

        for (DrinkHibernate drink : bill.getDrinks()) {
            this.drinks.add(drink.getId());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getDishes() {
        if (this.dishes == null) {
            this.dishes = new ArrayList<>();
        }
        return dishes;
    }

    public void setDishes(List<Long> dishes) {
        this.dishes = dishes;
    }

    public List<Long> getDrinks() {
        if (this.drinks == null) {
            this.drinks = new ArrayList<>();
        }
        return drinks;
    }

    public void setDrinks(List<Long> drinks) {
        this.drinks = drinks;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
