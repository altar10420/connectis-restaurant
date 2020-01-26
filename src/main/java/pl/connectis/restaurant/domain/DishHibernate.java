package pl.connectis.restaurant.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "dish")
public class DishHibernate {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isAvailable;

    //TODO check if this relation is set in a correct way, should it be PERSIST???
    @ManyToMany(mappedBy = "dishes", cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BillHibernate> bills;

    public DishHibernate() {
    }

    public DishHibernate(Long id,
                         String name,
                         String description,
                         BigDecimal price,
                         Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {return  name;}

    public void setName(String name) {this.name = name; }

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

    public List<BillHibernate> getBills() {
        if (this.bills == null) {
            this.bills = new ArrayList<>();
        }
        return bills;
    }

    public void setBills(List<BillHibernate> bills) {
        this.bills = bills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishHibernate that = (DishHibernate) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DishHibernate.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .add("description='" + description + "'")
                .add("price=" + price)
                .add("isAvailable=" + isAvailable)
                .toString();
    }
}
