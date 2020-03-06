package pl.connectis.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.DrinkHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface DrinkHibernateRepository
        extends PagingAndSortingRepository<DrinkHibernate, Long> {

    default DrinkHibernate getById(Long drinkId) {
        return findById(drinkId).orElseThrow(
                () -> new EntityDoesNotExistException("Drink id: " + drinkId + " not found"));
    }
}
