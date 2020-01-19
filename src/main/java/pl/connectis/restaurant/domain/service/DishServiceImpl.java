package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.domain.model.Dish;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService{

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Long createDish(String name,
                           String description,
                           BigDecimal price,
                           Boolean isAvailable) {
        Dish dish = dishRepository.createDish(
                name,
                description,
                price,
                isAvailable
        );
        return dish.getId();
    }

    @Override
    public Optional<Dish> getDish(Long id) {
        return dishRepository.getDish(id);
    }

    @Override
    public List<Dish> getAllDishes(Pageable pageable) {
        return dishRepository.getAllDishes(pageable);
    }

    @Override
    public List<Dish> getDishMenuPage(int page) {
        return dishRepository.getDishMenuPage(page);
    }

    @Override
    public void removeDish(Long id) {

    }
}
