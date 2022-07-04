package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.TypeProductDto;
import ru.chel.SRMPlayGround.model.TypeProduct;
import ru.chel.SRMPlayGround.repostitory.TypeProductRepo;
import ru.chel.SRMPlayGround.service.TypeProductService;

import java.util.List;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    private TypeProductRepo typeProductRepo;

    public TypeProductServiceImpl(TypeProductRepo typeProductRepo) {
        this.typeProductRepo = typeProductRepo;
    }

    @Override
    public List<TypeProduct> findAll() {
        return typeProductRepo.findAll();
    }

    @Override
    public TypeProduct findById(Long id) {
        return typeProductRepo.findById(id).orElse(null);
    }

    @Override
    public TypeProduct findByName(String name) {
        return typeProductRepo.findByName(name);
    }

    @Override
    public TypeProduct save(TypeProductDto productDto) {
        TypeProduct product = new TypeProduct();
        product.setName(productDto.getName());
        return typeProductRepo.save(product);
    }

    @Override
    public TypeProduct update(TypeProduct product) {
        return typeProductRepo.save(product);
    }

    @Override
    public void delete(TypeProduct typeProduct) {
        typeProductRepo.delete(typeProduct);
    }
}
