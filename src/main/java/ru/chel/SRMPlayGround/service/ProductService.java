package ru.chel.SRMPlayGround.service;

import ru.chel.SRMPlayGround.dto.ProductDto;
import ru.chel.SRMPlayGround.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product findByName(String name);
    Product save(ProductDto product);
    Product update(Product productDto);
    void delete(Product product);
}
