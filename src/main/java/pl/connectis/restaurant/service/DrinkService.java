package pl.connectis.restaurant.service;

import pl.connectis.restaurant.domain.DrinkHibernate;

import java.math.BigDecimal;
import java.util.List;

public interface DrinkService {

    Long createDrink(
            String name,
            String description,
            BigDecimal price,
            Boolean isAvailable,
            BigDecimal portion_ml
    );

    DrinkHibernate getDrink(Long id);

    List<DrinkHibernate> getAllDrinks();

    List<DrinkHibernate> getDrinksByPage(int page, int amountOnPage);

    void updateDrink(Long id, String name, String description, BigDecimal price, Boolean isAvailable, BigDecimal portion_ml);

    void removeDrink(Long id);
}
