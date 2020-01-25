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

    List<ProductHibernate> getProductMenuPage(int page);

    void updateProduct(Long id, String name, BigInteger stored_amount);

    void removeProduct(Long id);
}
