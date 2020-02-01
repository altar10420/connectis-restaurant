package pl.connectis.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.DishHibernate;

public interface DishHibernateRepository
        extends PagingAndSortingRepository<DishHibernate, Long> {

}
