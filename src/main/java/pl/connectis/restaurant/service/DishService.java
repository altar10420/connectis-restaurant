package pl.connectis.restaurant.service;

import pl.connectis.restaurant.domain.DishHibernate;

import java.math.BigDecimal;
import java.util.List;

public interface DishService {

    Long createDish(
            String name,
            String description,
            BigDecimal price,
            Boolean isAvailable
    );

    DishHibernate getDish(Long id);

    List<DishHibernate> getAllDishes();

    List<DishHibernate> getDishesByPage(int page, int amountOnPage);

    void updateDish(Long id, String name, String description, BigDecimal price, Boolean isAvailable);

    void removeDish(Long id);
}
