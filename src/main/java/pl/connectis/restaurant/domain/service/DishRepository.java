package pl.connectis.restaurant.domain.service;

import pl.connectis.restaurant.domain.model.Bill;
import pl.connectis.restaurant.domain.model.Dish;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DishRepository {

    Dish createDish(
            String name,
            String description,
            BigDecimal price,
            Boolean isAvailable
    );

    Optional<Dish> getDish(Long id);

    List<Dish> getAllDishes(Pageable pageable);

    void removeDish(Long id);
}
