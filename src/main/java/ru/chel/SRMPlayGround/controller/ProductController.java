package ru.chel.SRMPlayGround.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.ProductDto;
import ru.chel.SRMPlayGround.dto.customer.CustomerDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.model.TypeProduct;
import ru.chel.SRMPlayGround.service.ProductService;
import ru.chel.SRMPlayGround.service.TypeProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    private TypeProductService typeProductService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService, TypeProductService typeProductService) {
        this.productService = productService;
        this.typeProductService = typeProductService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Product> product = productService.findAll();
        if (product.isEmpty()){
            String error = "Список продуктов пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(product).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Product product = productService.findById(id);

        if (product == null){
            String error = String.format("Продукт с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(product).build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDto requestDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (typeProductService.findById(requestDto.getParent()) == null){
            String error = String.format("Тип продукта с id: '%s' не найден", requestDto.getParent());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (productService.findByName(requestDto.getName()) != null){
            String error = String.format("Продукт с именем: '%s' уже существует", requestDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        productService.save(requestDto);

        String result = String.format("Продукт добавлен: %s", requestDto);
        logger.error(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (typeProductService.findById(productDto.getParent()) == null){
            String error = String.format("Тип продукта с id: '%s' не найден", productDto.getParent());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (productService.findById(id) == null){
            String error = String.format("Продукт с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (productService.findByName(productDto.getName()) != null){
            String error = String.format("Продукт с именем: '%s' уже существует", productDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        Product product = productService.findById(id);
        product.setName(productDto.getName());
        product.setTypeProduct(typeProductService.findById(productDto.getParent()));
        productService.update(product);

        String result = String.format("Продукт обновлен: %s", productDto);
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (productService.findById(id) == null){
            String error = String.format("Продукт с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            Product product = productService.findById(id);
            productService.delete(product);

            String result = String.format("Продукт с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении продукта с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
