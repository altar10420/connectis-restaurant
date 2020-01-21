package pl.connectis.restaurant.domain.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Bill;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillRepository {

    Bill createBill(
            String name,
            String description,
            BigDecimal price,
            Boolean isAvailable
    );

    Optional<Dish> getDish(Long id);

    List<Dish> getAllDishes(Pageable pageable);

    List<Dish> getDishMenuPage(int page);

    void removeDish(Long id);
}
