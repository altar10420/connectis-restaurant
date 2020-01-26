package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.DishHibernateRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DishServiceImpl implements DishService {

    private final DishHibernateRepository dishHibernateRepository;

    @Autowired
    public DishServiceImpl(DishHibernateRepository dishHibernateRepository) {
        this.dishHibernateRepository = dishHibernateRepository;
    }

    @Override
    public Long createDish(String name,
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
        return dishHibernate.getId();
    }

    @Override
    public Optional<DishHibernate> getDish(Long id) {

        Optional<DishHibernate> dishOptional = dishHibernateRepository.findById(id);

        if(!dishOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return dishHibernateRepository.findById(id);
    }

    @Override
    public List<DishHibernate> getAllDishes(Pageable pageable) {
        Page<DishHibernate> page = dishHibernateRepository.findAll(pageable);
        List<DishHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishHibernate> getDishMenuPage(int page) {
        Page<DishHibernate> dishHibernateList = dishHibernateRepository.findAll(PageRequest.of(page, 10));
        List<DishHibernate> hibernates = dishHibernateList.getContent();

        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDish(Long id, String name, String description, BigDecimal price, Boolean isAvailable){
        Optional<DishHibernate> optionalDishHibernate = dishHibernateRepository.findById(id);
        if (!optionalDishHibernate.isPresent()){
            throw new EntityDoesNotExistException();
        }

        DishHibernate dishHibernate = optionalDishHibernate.get();
        dishHibernate.setName(name);
        dishHibernate.setDescription(description);
        dishHibernate.setPrice(price);
        dishHibernate.setAvailable(isAvailable);

        dishHibernateRepository.save(dishHibernate);
    }

    @Override
    public void removeDish(Long id) {
        dishHibernateRepository.deleteById(id);
    }

    public DishHibernate toDomain(DishHibernate hibernate) {
        return new DishHibernate(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getDescription(),
                hibernate.getPrice(),
                hibernate.getAvailable()
        );
    }
}
