package ru.chel.SRMPlayGround.service;

import ru.chel.SRMPlayGround.dto.productList.ProductListDto;
import ru.chel.SRMPlayGround.dto.productList.UpdateProductListDto;
import ru.chel.SRMPlayGround.model.ProductList;

import java.util.List;

public interface ProductListService {
    List<ProductList> findAll();
    List<ProductList> findByOrderId(Long id);
    List<ProductList> findByProductsId(Long id);
    ProductList save(ProductListDto dto);
    ProductList findById(Long id);
    ProductList updateCount(Long id, UpdateProductListDto dto);
    ProductList findByOrdersIdAndProductId(Long idOrder, Long idProduct);
    void delete(ProductList productList);
    void deleteAll(List<ProductList> productLists);
}
