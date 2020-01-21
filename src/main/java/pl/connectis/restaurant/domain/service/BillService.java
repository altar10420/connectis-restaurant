package pl.connectis.restaurant.domain.service;

import pl.connectis.restaurant.domain.model.Bill;
import pl.connectis.restaurant.domain.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BillService {

    Long createBill(
            LocalDateTime date,
            BigDecimal price,
            BigDecimal tip,
            List<Dish> dishes
    );

    Optional<Bill> getBill(Long id);
}
