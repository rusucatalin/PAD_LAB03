package com.example.demo.web;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductResources {
  private final Logger log = LoggerFactory.getLogger(ProductResources.class);
  private final ProductService productService;

  public ProductResources(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    log.info("REST request to get product by id: {}", id);
    Product product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

}
