package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.ProductDto;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.repostitory.ProductRepo;
import ru.chel.SRMPlayGround.service.ProductService;
import ru.chel.SRMPlayGround.service.TypeProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private TypeProductService typeProductService;

    public ProductServiceImpl(ProductRepo productRepo, TypeProductService typeProductService) {
        this.productRepo = productRepo;
        this.typeProductService = typeProductService;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product findByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public Product save(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setTypeProduct(typeProductService.findById(productDto.getParent()));
        return productRepo.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }
}
