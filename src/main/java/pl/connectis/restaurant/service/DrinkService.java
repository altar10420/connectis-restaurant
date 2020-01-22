package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.DrinkHibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DrinkService {
    Long createDrink(
            String name,
            String description,
            BigDecimal price,
            Boolean is_available,
            BigDecimal portion_ml
    );

    Optional<DrinkHibernate> getDrink(Long id);

    List<DrinkHibernate> getAllDrinks(Pageable pageable);

    List<DrinkHibernate> getDrinkMenuPage(int page);

    void removeDrink(Long id);
}
