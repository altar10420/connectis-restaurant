package pl.connectis.restaurant.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drink")
public class DrinkHibernate {

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

    @Column(nullable = false)
    private BigDecimal portion_ml;

    @ManyToMany(mappedBy = "drinks", cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BillHibernate> bills;

    public DrinkHibernate(Long id,
                          String name,
                          String description,
                          BigDecimal price,
                          Boolean isAvailable,
                          BigDecimal portion_ml) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.portion_ml = portion_ml;
    }

    public DrinkHibernate() {

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

    public BigDecimal getPortion_ml() {
        return portion_ml;
    }

    public void setPortion_ml(BigDecimal portion_ml) {
        this.portion_ml = portion_ml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkHibernate drinkHibernate = (DrinkHibernate) o;
        return Objects.equals(id, drinkHibernate.id);
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
                ", isAvailable=" + isAvailable +
                ", portion_ml=" + portion_ml +
                '}';
    }
}
