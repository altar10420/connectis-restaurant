package pl.connectis.restaurant.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.infrastructure.entity.DishHibernate;

public interface DishHibernateRepository
        extends PagingAndSortingRepository<DishHibernate, Long> {

    Page<DishHibernate> findByNameContaining(String partOfName, Pageable pageable);
}
