package pl.connectis.restaurant.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.connectis.restaurant.domain.ProductHibernate;
import pl.connectis.restaurant.service.ProductService;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductServiceImpl implements pl.connectis.restaurant.service.ProductService {
    @Override
    public ProductHibernate createProduct(String name, BigInteger stored_amount) {
        return null;
    }

    @Override
    public Optional<ProductHibernate> getProduct(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProductHibernate> getAllProduct(Pageable pageable) {
        return null;
    }

    @Override
    public List<ProductHibernate> getProductMenuPage(int page) {
        return null;
    }

    @Override
    public void removeProduct(Long id) {

    }
}
