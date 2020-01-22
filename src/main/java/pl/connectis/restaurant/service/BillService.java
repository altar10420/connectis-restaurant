package pl.connectis.restaurant.service;

import pl.connectis.restaurant.domain.BillHibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BillService {

    Long createBill(
            LocalDateTime date,
            BigDecimal price,
            BigDecimal tip,
            List<Long> dishes
    );

    Optional<BillHibernate> getBill(Long billId);

    //TODO other methods




//    public BillHibernate(Long id,
//                         LocalDateTime date,
//                         BigDecimal price,
//                         BigDecimal tip) {
}
