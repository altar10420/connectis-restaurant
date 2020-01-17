package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Drink;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DrinkServiceImpl  implements DrinkService{

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Long createDrink(String name, String description, double price, Boolean is_available, int portion_ml) {
        return null;
    }

    @Override
    public Optional<Drink> getDrink(Long id) {
        return drinkRepository.getDrink(id);
    }

    @Override
    public List<Drink> getAllDrinks(Pageable pageable) {
        return null;
    }

    @Override
    public void removeDrink(Long id) {

    }
}
