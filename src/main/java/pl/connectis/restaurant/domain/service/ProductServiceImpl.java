package pl.connectis.restaurant.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.domain.model.Dish;
import pl.connectis.restaurant.domain.model.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long createProduct(String name,
            BigInteger stored_amount) {
        Product product = productRepository.createProduct(
                name,
                stored_amount
        );
        return product.getId();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> getAllProduct(Pageable pageable) {
        return productRepository.getAllProduct(pageable);
    }

    @Override
    public List<Product> getProductMenuPage(int page) {
        return productRepository.getProductMenuPage(page);
    }

    @Override
    public void removeProduct(Long id) {

    }
}
