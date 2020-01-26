package pl.connectis.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.DrinkHibernateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
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
    public Optional<DrinkHibernate> getDrink(Long id) {
        Optional<DrinkHibernate> drinkOptional = drinkHibernateRepository.findById(id);

        if(!drinkOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return drinkHibernateRepository.findById(id);
    }

    @Override
    public List<DrinkHibernate> getAllDrinks(Pageable pageable) {
        Page<DrinkHibernate> page = drinkHibernateRepository.findAll(pageable);
        List<DrinkHibernate> hibernates = page.getContent();
        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinkHibernate> getDrinkMenuPage(int page) {
        Page<DrinkHibernate> drinkList = drinkHibernateRepository.findAll(PageRequest.of(page, 10));
        List<DrinkHibernate> hibernates = drinkList.getContent();

        return hibernates.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDrink(Long id, String name, String description, BigDecimal price, Boolean isAvailable, BigDecimal portion_ml) {
        Optional<DrinkHibernate> drinkHibernateOptional = drinkHibernateRepository.findById(id);

        if (!drinkHibernateOptional.isPresent()){
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

    public DrinkHibernate toDomain(DrinkHibernate hibernate) {
        return new DrinkHibernate(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getDescription(),
                hibernate.getPrice(),
                hibernate.getAvailable(),
                hibernate.getPortion_ml()
        );
    }
}
