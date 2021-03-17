package br.com.siecola.aws_ddb_sqs_demo.controller;

import br.com.siecola.aws_ddb_sqs_demo.enums.EventType;
import br.com.siecola.aws_ddb_sqs_demo.model.Product;
import br.com.siecola.aws_ddb_sqs_demo.model.ProductDto;
import br.com.siecola.aws_ddb_sqs_demo.repository.ProductRepository;
import br.com.siecola.aws_ddb_sqs_demo.service.ProductEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(
            ProductController.class);
    private final ProductRepository productRepository;
    private final ProductEventPublisher productEventPublisher;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductEventPublisher productEventPublisher) {
        this.productRepository = productRepository;
        this.productEventPublisher = productEventPublisher;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{username}")
    public List<ProductDto> findByUsername(@PathVariable String username) {
        return productRepository.findAllByPk(username)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{username}/{code}")
    public ResponseEntity<ProductDto> findByUsernameAndCode(@PathVariable String username, @PathVariable String code) {
        return productRepository.findByPkAndSk(username, code)
                .map(product -> new ResponseEntity<>(new ProductDto(product), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> saveProduct(
            @RequestBody ProductDto productDto) {
        Product productCreated = productRepository.save(new Product(productDto));

        productEventPublisher.publishProductEvent(productCreated,
                EventType.PRODUCT_CREATED);

        return new ResponseEntity<ProductDto>(new ProductDto(productCreated),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/products/{username}/{code}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable("username") String username,
                                                    @PathVariable("code") String code) {
        if (productRepository.findByPkAndSk(username, code).isPresent()) {
            Product productUpdated = productRepository.save(new Product(productDto));

            productEventPublisher.publishProductEvent(productUpdated,
                    EventType.PRODUCT_UPDATED);

            return new ResponseEntity<ProductDto>(new ProductDto(productUpdated),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/products/{username}/{code}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("username") String username,
                                                 @PathVariable("code") String code) {
        Optional<Product> optProduct = productRepository.findByPkAndSk(username, code);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();

            productRepository.delete(product);

            productEventPublisher.publishProductEvent(product,
                    EventType.PRODUCT_DELETED);

            return new ResponseEntity<ProductDto>(new ProductDto(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}