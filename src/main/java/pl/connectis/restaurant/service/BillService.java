package pl.connectis.restaurant.service;

import pl.connectis.restaurant.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BillService {

    Long createBill(
            LocalDateTime date,
            BigDecimal price,
            BigDecimal tip,
            List<DishHibernate> dishes,
            List<DrinkHibernate> drinks,
            Long clientId,
            Long employeeId
    );

    Optional<BillHibernate> getBill(Long billId);

    Long addDish(Long billId, Long dishId);

    Long addDrink(Long billId, Long drinkId);

    void removeBill(Long billId);

}
