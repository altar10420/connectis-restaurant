package pl.connectis.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.domain.ProductHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;

public interface ProductHibernateRepository
        extends PagingAndSortingRepository<ProductHibernate, Long> {

    default ProductHibernate getById(Long productId) {
        return findById(productId).orElseThrow(
                () -> new EntityDoesNotExistException("Product id: " + productId + " not found"));
    }
}
