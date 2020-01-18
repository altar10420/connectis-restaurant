package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Drink;
import pl.connectis.restaurant.domain.service.DrinkRepository;
import pl.connectis.restaurant.infrastructure.entity.DrinkHibernate;
import pl.connectis.restaurant.infrastructure.repository.DrinkHibernateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DrinkRepositoryImpl implements DrinkRepository {

    private final DrinkHibernateRepository drinkHibernateRepository;

    @Autowired
    public DrinkRepositoryImpl(DrinkHibernateRepository drinkHibernateRepository) {
        this.drinkHibernateRepository = drinkHibernateRepository;
    }

    @Override
    public Drink createDrink(String name,
                           String description,
                           BigDecimal price,
                           Boolean isAvailable,
                           BigDecimal portion_ml) {
        DrinkHibernate drinkHibernate = new DrinkHibernate(
                null,
                name,
                description,
                price,
                isAvailable,
                portion_ml
        );

        drinkHibernateRepository.save(drinkHibernate);
        return toDomain(drinkHibernate);
    }

    @Override
    public Optional<Drink> getDrink(Long id) {
        return drinkHibernateRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Drink> getAllDrinks(Pageable pageable) {
        Page<DrinkHibernate> page = drinkHibernateRepository.findAll(pageable);
        List<DrinkHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void removeDrink(Long id) {
        drinkHibernateRepository.deleteById(id);
    }

    public Drink toDomain(DrinkHibernate hibernate) {
        return new Drink(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getDescription(),
                hibernate.getPrice(),
                hibernate.getIs_available(),
                hibernate.getPortion_ml()
        );
    }
}
