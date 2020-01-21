package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.domain.model.Drink;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Long createDrink(String name, String description, BigDecimal price, Boolean is_available, BigDecimal portion_ml) {
        {
            Drink drink = drinkRepository.createDrink(
                    name,
                    description,
                    price,
                    is_available,
                    portion_ml
            );
            return drink.getId();
        }

        @Override
        public Optional<Drink> getDrink (Long id){
            return drinkRepository.getDrink(id);
        }

        @Override
        public List<Drink> getAllDrinks (Pageable pageable){
            return drinkRepository.getAllDrinks(pageable);
        }

        @Override
        public List<Drink> getDrinkMenuPage ( int page){
            return DrinkRepository.getDrinkMenuPage(page);
        }

        @Override
        public void removeDrink (Long id){

        }
    }
}
