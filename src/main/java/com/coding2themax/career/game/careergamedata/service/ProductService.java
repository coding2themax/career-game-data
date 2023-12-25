package com.coding2themax.career.game.careergamedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding2themax.career.game.careergamedata.model.Product;
import com.coding2themax.career.game.careergamedata.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public Flux<Product> getAllProducts() {
    return this.repository.findAll();
  }

  public Mono<Product> getProductById(int productId) {
    return this.repository.findById(productId);
  }

  public Mono<Product> createProduct(final Product product) {
    return this.repository.save(product);
  }

  public Mono<Product> updateProduct(int productId, final Mono<Product> productMono) {
    return this.repository.findById(productId)
        .flatMap(p -> productMono.map(u -> {
          p.setDescription(u.getDescription());
          p.setPrice(u.getPrice());
          return p;
        }))
        .flatMap(p -> this.repository.save(p));
  }

  public Mono<Void> deleteProduct(final int id) {
    return this.repository.deleteById(id);
  }

}
