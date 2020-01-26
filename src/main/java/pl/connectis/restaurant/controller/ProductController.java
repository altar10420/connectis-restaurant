package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.EmployeeDTO;
import pl.connectis.restaurant.controller.dto.ProductDTO;
import pl.connectis.restaurant.domain.ProductHibernate;
import pl.connectis.restaurant.exception.EntityDoesNotExistException;
import pl.connectis.restaurant.repository.ProductHibernateRepository;
import pl.connectis.restaurant.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductHibernateRepository productHibernateRepository;

    @Autowired
    public ProductController(ProductService productService, ProductHibernateRepository productHibernateRepository) {
        this.productService = productService;
        this.productHibernateRepository = productHibernateRepository;
    }

    @GetMapping(path = "/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id) {
        if(!productService.getProduct(id).isPresent()) {
            throw new EntityDoesNotExistException();
        }
        return new ProductDTO(productService.getProduct(id).get());
    }

    @PostMapping(path = "/")
    public Long createProduct(@RequestBody ProductDTO productDTO) {
        Long productId = productService.createProduct(
                productDTO.getName(),
                productDTO.getStored_amount()
        );
        return productId;
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {

        Optional<ProductHibernate> productOptional = productHibernateRepository.findById(id);

        if(!productOptional.isPresent()) {
            throw new EntityDoesNotExistException();
        }
        productService.updateProduct(id,
                productDTO.getName(),
                productDTO.getStored_amount()
        );
    }

    @DeleteMapping(path = "/{id}")
    public void removeProduct(@PathVariable("id") Long id) {
        productService.removeProduct(id);
    }
}
