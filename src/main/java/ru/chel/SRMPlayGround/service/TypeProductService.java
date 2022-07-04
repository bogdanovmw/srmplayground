package ru.chel.SRMPlayGround.service;



import ru.chel.SRMPlayGround.dto.TypeProductDto;
import ru.chel.SRMPlayGround.model.TypeProduct;

import java.util.List;

public interface TypeProductService {
    List<TypeProduct> findAll();
    TypeProduct findById(Long id);
    TypeProduct findByName(String name);
    TypeProduct save(TypeProductDto product);
    TypeProduct update(TypeProduct product);
    void delete(TypeProduct typeProduct);
}
