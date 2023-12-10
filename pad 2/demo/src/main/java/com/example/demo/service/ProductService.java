package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
  private final Logger log = LoggerFactory.getLogger(ProductService.class);
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product getProductById(Long id) {
    log.info("Request to db to get product by id : {}", id);
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    return product;
  }

  public Product edit(Product dto) {
    log.info("Request to db to edit product by id : {}", dto.getId());
    Product product = getProductById(dto.getId());
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    return productRepository.save(product);
  }
}
