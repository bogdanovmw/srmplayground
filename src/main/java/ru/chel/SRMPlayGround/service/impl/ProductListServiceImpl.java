package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.productList.ProductListDto;
import ru.chel.SRMPlayGround.dto.productList.UpdateProductListDto;
import ru.chel.SRMPlayGround.model.*;
import ru.chel.SRMPlayGround.repostitory.ProductListRepo;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.ProductListService;
import ru.chel.SRMPlayGround.service.ProductService;

import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {
    private ProductListRepo productListRepo;
    private ProductService productService;
    private OrdersService ordersService;

    public ProductListServiceImpl(ProductListRepo productListRepo, ProductService productService, OrdersService ordersService) {
        this.productListRepo = productListRepo;
        this.productService = productService;
        this.ordersService = ordersService;
    }

    @Override
    public List<ProductList> findAll() {
        return productListRepo.findAll();
    }

    @Override
    public List<ProductList> findByOrderId(Long id) {
        return productListRepo.findByOrdersId(id);
    }

    @Override
    public List<ProductList> findByProductsId(Long id) {
        return productListRepo.findByProductId(id);
    }


    @Override
    public ProductList save(ProductListDto dto) {
        ProductList productList = new ProductList();
        Product product = productService.findById(dto.getProductId());
        Orders orders = ordersService.findById(dto.getOrderId());

        productList.setOrders(orders);
        productList.setProduct(product);
        productList.setCount(dto.getCount());
        productList.setType(dto.getType());

        return productListRepo.save(productList);
    }

    @Override
    public ProductList findById(Long id) {
        return productListRepo.findById(id).orElse(null);
    }

    @Override
    public ProductList updateCount(Long id, UpdateProductListDto dto) {
        ProductList productList = productListRepo.findById(id).orElse(null);
        productList.setCount(dto.getCount());
        productList.setType(dto.getType());
        return productListRepo.save(productList);
    }

    @Override
    public ProductList findByOrdersIdAndProductId(Long idOrder, Long idProduct) {
        return productListRepo.findByOrdersIdAndProductId(idOrder, idProduct);
    }

    @Override
    public void delete(ProductList productList) {
        productListRepo.delete(productList);
    }

    @Override
    public void deleteAll(List<ProductList> productLists) {
        productListRepo.deleteAll(productLists);
    }

}
