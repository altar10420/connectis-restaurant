package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.DrinkHibernateRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkHibernateRepository drinkHibernateRepository;

    @Autowired
    public DrinkServiceImpl(DrinkHibernateRepository drinkHibernateRepository) {
        this.drinkHibernateRepository = drinkHibernateRepository;
    }

    @Override
    public Long createDrink(String name,
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
        return drinkHibernate.getId();
    }

    @Override
    public DrinkHibernate getDrink(Long id) {

        return drinkHibernateRepository.getById(id);
    }

    @Override
    public List<DrinkHibernate> getAllDrinks() {

        Iterable<DrinkHibernate> drinks = drinkHibernateRepository.findAll();

        List<DrinkHibernate> drinkList = new ArrayList<>();

        for (DrinkHibernate drink : drinks) {
            drinkList.add(drink);
        }

        return drinkList;
    }

    @Override
    public List<DrinkHibernate> getDrinksByPage(int page, int amountOnPage) {
        Page<DrinkHibernate> drinkHibernateList = drinkHibernateRepository
                .findAll(PageRequest.of(page - 1, amountOnPage));

        return drinkHibernateList.stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDrink(Long id, String name, String description, BigDecimal price, Boolean isAvailable, BigDecimal portion_ml) {
        Optional<DrinkHibernate> drinkHibernateOptional = drinkHibernateRepository.findById(id);

        if (!drinkHibernateOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        DrinkHibernate drinkHibernate = drinkHibernateOptional.get();
        drinkHibernate.setName(name);
        drinkHibernate.setDescription(description);
        drinkHibernate.setPrice(price);
        drinkHibernate.setAvailable(isAvailable);
        drinkHibernate.setPortion_ml(portion_ml);

        drinkHibernateRepository.save(drinkHibernate);
    }

    @Override
    public void removeDrink(Long id) {
        drinkHibernateRepository.deleteById(id);
    }
}
