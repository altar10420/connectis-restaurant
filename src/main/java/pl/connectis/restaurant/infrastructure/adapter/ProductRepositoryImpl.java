package pl.connectis.restaurant.infrastructure.adapter;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.model.Product;
import pl.connectis.restaurant.domain.service.ProductRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product createProduct(String name, BigInteger stored_amount) {
        return null;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProduct(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> getProductMenuPage(int page) {
        return null;
    }

    @Override
    public void removeProduct(Long id) {

    }
}
