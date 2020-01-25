package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.DishHibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DishService {

    Long createDish(
            String name,
            String description,
            BigDecimal price,
            Boolean isAvailable
    );

    Optional<DishHibernate> getDish(Long id);

    List<DishHibernate> getAllDishes(Pageable pageable);

    List<DishHibernate> getDishMenuPage(int page);

    void updateDish(Long id, String name, String description, BigDecimal price, Boolean isAvailable);

    void removeDish(Long id);
}
