package pl.connectis.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.controller.dto.ProductDTO;
import pl.connectis.restaurant.domain.ProductHibernate;
import pl.connectis.restaurant.repository.ProductHibernateRepository;
import pl.connectis.restaurant.service.ProductService;

import java.util.ArrayList;
import java.util.List;
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
        Optional<ProductHibernate> productOptional = productService.getProduct(id);
        //TODO throw some exception if failed
        return new ProductDTO(productOptional.get());
    }

    @GetMapping(path = "/menu/{page}")
    public List<ProductDTO> getProductMenuPage(@PathVariable("page") Integer page) {
        List<ProductHibernate> productList = productService.getProductMenuPage(page);
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (ProductHibernate product : productList) {
            productDTOList.add(new ProductDTO(product));
        }
        //TODO throw some message/exception if failed
        return productDTOList;
    }

    @PostMapping(path = "/")
    public Long createProduct(@RequestBody ProductDTO productDTO) {
        ProductHibernate productId = productService.createProduct(
                productDTO.getName(),
                productDTO.getStored_amount()
        );
        //TODO throw some message/exception if failed
        return productId;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductHibernate> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        Optional<ProductHibernate> productHibernateOptional = productHibernateRepository.findById(id);
        ProductHibernate _productHibernate = productHibernateOptional.get();
        if(productHibernateOptional.isPresent()){
            _productHibernate.setName(productDTO.getName());
            _productHibernate.setStored_amount(productDTO.getStored_amount());
        }
        return new ResponseEntity<>(productHibernateRepository.save(_productHibernate), HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productService.removeProduct(id);
        //TODO throw some message/exception if failed
        return "REMOVED";
    }
}
