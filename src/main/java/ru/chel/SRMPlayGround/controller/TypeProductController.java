package ru.chel.SRMPlayGround.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.TypeProductDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.TypeProduct;
import ru.chel.SRMPlayGround.service.TypeProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/type-product")
public class TypeProductController {
    private TypeProductService typeProductService;
    private static final Logger logger = LoggerFactory.getLogger(TypeProductController.class);

    public TypeProductController(TypeProductService typeProductService) {
        this.typeProductService = typeProductService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<TypeProduct> typeProducts = typeProductService.findAll();

        if (typeProducts.isEmpty()){
            String error = "Список типов продуктов пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(typeProducts).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        TypeProduct typeProduct = typeProductService.findById(id);

        if (typeProduct == null){
            String error = String.format("Тип продукта с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(typeProduct).build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TypeProductDto typeProduct, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (typeProductService.findByName(typeProduct.getName()) != null){
            String error = String.format("Тип продукта с именем: '%s' уже существует", typeProduct.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        typeProductService.save(typeProduct);

        String result = String.format("Тип продукта добавлен: %s", typeProduct);
        logger.error(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TypeProductDto productDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (typeProductService.findById(id) == null){
            String error = String.format("Тип продукта с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (typeProductService.findByName(productDto.getName()) != null){
            String error = String.format("Тип продукта с именем: '%s' уже существует", productDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        TypeProduct product = typeProductService.findById(id);
        product.setName(productDto.getName());
        typeProductService.update(product);

        String result = String.format("Данные с типом продукта были обновлены: %s", productDto);
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (typeProductService.findById(id) == null){
            String error = String.format("Тип продукта с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            TypeProduct typeProduct = typeProductService.findById(id);
            typeProductService.delete(typeProduct);

            String result = String.format("Тип продукта с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении типом продукта с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
