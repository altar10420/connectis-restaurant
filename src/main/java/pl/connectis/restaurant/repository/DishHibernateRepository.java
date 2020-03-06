package pl.connectis.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.DishHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface DishHibernateRepository
        extends PagingAndSortingRepository<DishHibernate, Long> {

    default DishHibernate getById(Long dishId) {
        return findById(dishId).orElseThrow(
                () -> new EntityDoesNotExistException("Dish id: " + dishId + " not found"));
    }
}
