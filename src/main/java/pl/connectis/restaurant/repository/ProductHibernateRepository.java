package pl.connectis.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.ProductHibernate;

public interface ProductHibernateRepository
        extends PagingAndSortingRepository<ProductHibernate, Long> {
}
