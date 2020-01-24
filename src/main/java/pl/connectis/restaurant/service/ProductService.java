package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.ProductHibernate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Long createProduct(
            String name,
            BigInteger stored_amount
    );

    Optional<ProductHibernate> getProduct(Long id);

    List<ProductHibernate> getAllProduct(Pageable pageable);

    List<ProductHibernate> getProductMenuPage(int page);

    void removeProduct(Long id);
}
