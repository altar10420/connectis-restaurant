package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.model.Dish;
import pl.connectis.restaurant.domain.service.DishRepository;
import pl.connectis.restaurant.infrastructure.entity.DishHibernate;
import pl.connectis.restaurant.infrastructure.repository.DishHibernateRepository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class DishRepositoryImpl implements DishRepository {

    private final DishHibernateRepository dishHibernateRepository;

    @Autowired
    public DishRepositoryImpl(DishHibernateRepository dishHibernateRepository) {
        this.dishHibernateRepository = dishHibernateRepository;
    }

    @Override
    public Dish createDish(String name,
                           String description,
                           BigDecimal price,
                           Boolean isAvailable) {
        DishHibernate dishHibernate = new DishHibernate(
                null,
                name,
                description,
                price,
                isAvailable
        );

        dishHibernateRepository.save(dishHibernate);
        return toDomain(dishHibernate);
    }

    @Override
    public Optional<Dish> getDish(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Dish> getAllDishes(Pageable pageable) {
        return null;
    }

    @Override
    public void removeDish(Long id) {

    }

    public Dish toDomain(DishHibernate hibernate) {
        return new Dish(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getDescription(),
                hibernate.getPrice(),
                hibernate.getAvailable()
        );
    }
}
