package pl.connectis.restaurant.controller.dto;

import pl.connectis.restaurant.domain.BillHibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillDTO implements Serializable {

    private Long id;

    private LocalDateTime date;

    private BigDecimal price;

    private BigDecimal tip;

    public BillDTO() {
    }

    public BillDTO(BillHibernate bill) {
        this.id = bill.getId();
        this.date = bill.getDate();
        this.price = bill.getPrice();
        this.tip = bill.getTip();
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
}
