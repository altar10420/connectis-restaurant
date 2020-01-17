package pl.connectis.restaurant.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.infrastructure.entity.DishHibernate;
import pl.connectis.restaurant.infrastructure.entity.DrinkHibernate;

public interface DrinkHibernateRepository extends PagingAndSortingRepository<DrinkHibernate, Long> {

    Page<DrinkHibernate> findByNameContaining(String partOfName, Pageable pageable);
}
