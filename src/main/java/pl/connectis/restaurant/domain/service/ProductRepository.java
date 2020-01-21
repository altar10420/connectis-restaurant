package pl.connectis.restaurant.domain.service;

import org.springframework.data.domain.Pageable;
import pl.connectis.restaurant.domain.model.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product createProduct(
            Long id,
            String name,
            BigInteger stored_amount
    );

    Optional<Product> getProduct(Long id);

    List<Product> getAllProduct(Pageable pageable);

    List<Product> getProductMenuPage(int page);

    void removeProduct(Long id);
}
