package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.domain.EmployeeHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.DishHibernateRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
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
    public DishHibernate getDish(Long id) {

        return dishHibernateRepository.getById(id);
    }

    @Override
    public List<DishHibernate> getAllDishes() {

        Iterable<DishHibernate> dishes = dishHibernateRepository.findAll();

        List<DishHibernate> dishList = new ArrayList<>();

        for (DishHibernate dish : dishes) {
            dishList.add(dish);
        }

        return dishList;
    }

    @Override
    public List<DishHibernate> getDishesByPage(int page, int amountOnPage) {
        Page<DishHibernate> dishHibernateList = dishHibernateRepository
                .findAll(PageRequest.of(page - 1, amountOnPage));

        return dishHibernateList.stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDish(Long id, String name, String description, BigDecimal price, Boolean isAvailable) {
        Optional<DishHibernate> optionalDishHibernate = dishHibernateRepository.findById(id);
        if (!optionalDishHibernate.isPresent()) {
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
}
