package pl.connectis.restaurant.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "bill")
public class BillHibernate {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal tip;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
// it must be LAZY when multiple ManyToMany - otherwise it wants to get all the objects the same
// time, and raises MultipleBagFetchException
    @JoinTable(
            name = "dish_bill",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<DishHibernate> dishes;

    //TODO check what fetch and cascade should be here
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
// it must be LAZY when multiple ManyToMany - otherwise it wants to get all the objects the same
// time, and raises MultipleBagFetchException
    @JoinTable(
            name = "drink_bill",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private List<DrinkHibernate> drinks;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientHibernate client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeHibernate employee;

    public BillHibernate() {
    }

    public BillHibernate(Long id,
                         LocalDateTime date,
                         BigDecimal price,
                         BigDecimal tip,
                         List<DishHibernate> dishes,
                         List<DrinkHibernate> drinks,
                         ClientHibernate client,
                         EmployeeHibernate employee) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.tip = tip;
        this.dishes = dishes;
        this.drinks = drinks;
        this.client = client;
        this.employee = employee;
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

    public List<DishHibernate> getDishes() {
        if (this.dishes == null) {
            this.dishes = new ArrayList<>();
        }
        return dishes;
    }

    public void setDishes(List<DishHibernate> dishes) {
        this.dishes = dishes;
    }

    public List<DrinkHibernate> getDrinks() {
        if (this.drinks == null) {
            this.drinks = new ArrayList<>();
        }
        return drinks;
    }

    public void setDrinks(List<DrinkHibernate> drinks) {
        this.drinks = drinks;
    }

    public ClientHibernate getClient() {
        return client;
    }

    public void setClient(ClientHibernate client) {
        this.client = client;
    }

    public EmployeeHibernate getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeHibernate employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillHibernate that = (BillHibernate) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BillHibernate.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("date=" + date)
                .add("price=" + price)
                .add("tip=" + tip)
                .add("dishes=" + dishes)
                .add("drinks=" + drinks)
                .add("client=" + client)
                .add("employee=" + employee)
                .toString();
    }
}
