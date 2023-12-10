package com.example.demo.web;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductResources {
  private final Logger log = LoggerFactory.getLogger(ProductResources.class);
  private final ProductRepository productRepository;
  private final ProductService productService;

  public ProductResources(
      ProductRepository productRepository,
      ProductService productService
  ) {
    this.productRepository = productRepository;
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Product> save(@RequestBody Product dto) {
    log.info("REST request to save product.");
    Product product = productRepository.save(dto);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    log.info("REST request to delete product with id : {}", id);
    productRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/edit")
  public ResponseEntity<Product> edit(@RequestBody Product dto) {
    log.info("REST request to edit product with id : {}", dto.getId());
    Product product = productService.edit(dto);
    return ResponseEntity.ok(product);
  }


}
