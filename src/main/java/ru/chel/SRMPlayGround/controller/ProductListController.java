package ru.chel.SRMPlayGround.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.orders.ProductListDtoResponse;
import ru.chel.SRMPlayGround.dto.productList.ProductListDto;
import ru.chel.SRMPlayGround.dto.productList.UpdateProductListDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.model.ProductList;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.ProductListService;
import ru.chel.SRMPlayGround.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product-list")
public class ProductListController {
    private ProductListService productListService;
    private ProductService productService;
    private OrdersService ordersService;
    private static final Logger logger = LoggerFactory.getLogger(ProductListController.class);

    public ProductListController(ProductListService productListService, ProductService productService, OrdersService ordersService) {
        this.productListService = productListService;
        this.productService = productService;
        this.ordersService = ordersService;
    }


    @GetMapping
    public ResponseEntity<?> findAll(){
        List<ProductList> productLists = productListService.findAll();

        if (productLists.isEmpty()){
            String error = "Товарный лист пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(productLists).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        ProductList productList = productListService.findById(id);

        if (productList == null){
            String error = String.format("Не найден товарный лист c id: %s", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(productList).build());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> findOderById(@PathVariable Long id){
        List<ProductList> listsProduct = productListService.findByOrderId(id);

        if (listsProduct.isEmpty()){
            String error = String.format("Не найден товарный лист по заказу с id: %s", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(listsProduct).build());
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductListDto listDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        Product product = productService.findById(listDto.getProductId());
        Orders orders = ordersService.findById(listDto.getOrderId());

        if (product == null || orders == null){
            String error = String.format("Не найден заказ: %s или не найден товар: %s",
                    orders != null ? orders.getId() : listDto.getOrderId(), product != null ? product.getName() : listDto.getProductId());

            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        ProductList orderAndProduct = productListService.findByOrdersIdAndProductId(listDto.getOrderId(), listDto.getProductId());
        if (orderAndProduct != null){
            String error = String.format("Продукт: %s, уже есть в списке", orderAndProduct.getProduct().getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        productListService.save(listDto);

        String result = String.format("Товар: %s к заказу: %s успешно добавлен", product.getName(), orders.getId());
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateProductListDto dto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (productListService.findById(id) == null){
            String error = String.format("Не найден товар c id: %s", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        productListService.updateCount(id, dto);

        String result = String.format("Количество в заказе с id: %s успешно обновлено", id);
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (productListService.findById(id) == null){
            String error = String.format("Не найден товар c id: %s", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            ProductList productList = productListService.findById(id);
            productListService.delete(productList);

            String result = String.format("Товар с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении товара с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
