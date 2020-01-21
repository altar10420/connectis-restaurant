package pl.connectis.restaurant.domain.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Dish;
import pl.connectis.restaurant.domain.model.Drink;
import pl.connectis.restaurant.domain.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DrinkRepository {
    Drink createDrink(
            String name,
            String description,
            BigDecimal price,
            Boolean is_available,
            BigDecimal portion_ml
    );

    Optional<Drink> getDrink(Long id);

    List<Drink> getAllDrinks(Pageable pageable);

    List<Drink> getDrinkMenuPage(int page);

    void removeDrink(Long id);
}
