package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.model.Dish;
import pl.connectis.restaurant.domain.service.DishRepository;
import pl.connectis.restaurant.infrastructure.entity.DishHibernate;
import pl.connectis.restaurant.infrastructure.repository.DishHibernateRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        return dishHibernateRepository.findById(id).map(this::toDomain);
    }

    //TODO how to implement this in DishController???
    @Override
    public List<Dish> getAllDishes(Pageable pageable) {
        Page<DishHibernate> page = dishHibernateRepository.findAll(pageable);
        List<DishHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dish> getDishMenuPage(int page) {
        Page<DishHibernate> dishList = dishHibernateRepository.findAll(PageRequest.of(page, 10));
        List<DishHibernate> hibernates = dishList.getContent();

        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void removeDish(Long id) {
        dishHibernateRepository.deleteById(id);
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
