package pl.connectis.restaurant.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.restaurant.domain.ProductHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.ProductHibernateRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductServiceImpl implements pl.connectis.restaurant.service.ProductService {

    private final ProductHibernateRepository productHibernateRepository;

    public ProductServiceImpl(ProductHibernateRepository productHibernateRepository) {
        this.productHibernateRepository = productHibernateRepository;
    }

    @Override
    public Long createProduct(String name, BigInteger stored_amount) {
            ProductHibernate productHibernate = new ProductHibernate(
                    null,
                    name,
                    stored_amount
            );

            productHibernateRepository.save(productHibernate);
            return productHibernate.getId();
    }

    @Override
    public Optional<ProductHibernate> getProduct(Long id) {
        Optional<ProductHibernate> productOptional = productHibernateRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return productHibernateRepository.findById(id);
    }

    @Override
    public List<ProductHibernate> getProductMenuPage(int page) {
        return null;
    }

    @Override
    @Transactional
    public void updateProduct(Long id, String name, BigInteger stored_amount){
        Optional<ProductHibernate> optionalProductHibernate = productHibernateRepository.findById(id);
        if (!optionalProductHibernate.isPresent()){
            throw new EntityDoesNotExistException();
        }

        ProductHibernate productHibernate = optionalProductHibernate.get();
        productHibernate.setName(name);
        productHibernate.setStored_amount(stored_amount);

        productHibernateRepository.save(productHibernate);
    }

    @Override
    public void removeProduct(Long id) {
        productHibernateRepository.deleteById(id);
    }

    public ProductHibernate toDomain(ProductHibernate hibernate) {
        return new ProductHibernate(
                hibernate.getId(),
                hibernate.getName(),
                hibernate.getStored_amount()
        );
    }
}
