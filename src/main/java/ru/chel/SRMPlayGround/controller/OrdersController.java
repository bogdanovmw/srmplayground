package ru.chel.SRMPlayGround.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.OrdersDto;
import ru.chel.SRMPlayGround.dto.orders.OrderListDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.model.ProductList;
import ru.chel.SRMPlayGround.repostitory.OrdersRepo;
import ru.chel.SRMPlayGround.repostitory.ProductListRepo;
import ru.chel.SRMPlayGround.service.CustomerService;
import ru.chel.SRMPlayGround.service.DriverService;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.ProductListService;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private OrdersService ordersService;
    private DriverService driverService;
    private CustomerService customerService;
    private ModelMapper modelMapper;
    private ProductListService productListService;

    public OrdersController(OrdersService ordersService, DriverService driverService, CustomerService customerService, ModelMapper modelMapper, ProductListService productListService, OrdersRepo ordersRepo, ProductListRepo productListRepo) {
        this.ordersService = ordersService;
        this.driverService = driverService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.productListService = productListService;
    }

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Orders> orders = ordersService.findAll();
        if (orders.isEmpty()){
            String error = "Список заказов пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        Type type = new TypeToken<List<OrderListDto>>(){}.getType();
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(modelMapper.map(orders, type)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Orders orders = ordersService.findById(id);

        if (orders == null){
            String error = String.format("Заказ с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(modelMapper.map(orders, OrderListDto.class)).build());
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<?> findAllByDriver(@PathVariable Long id){
        List<Orders> orders = ordersService.findAllByDriverId(id);
        Type type = new TypeToken<List<OrderListDto>>(){}.getType();

        if (orders.isEmpty()){
            String error = String.format("Заказы по маршруту с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(modelMapper.map(orders, type)).build());
    }

    @GetMapping("/customer-driver/{driver}/{customer}")
    private ResponseEntity<?> findCustomerDriver(@PathVariable("driver") Long driver, @PathVariable("customer") Long customer){
        Orders orders = ordersService.findByCustomerIdAndDriverId(customer, driver);

        if (orders == null){
            String error = String.format("Заказ по маршруту: '%s' и магазину '%s' не найден", driver, customer);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(modelMapper.map(orders, OrderListDto.class)).build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrdersDto ordersDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;


        if (ordersService.findByCustomerIdAndDriverId(ordersDto.getCustomer(), ordersDto.getDriver()) != null){
            String error = String.format("Заказ с таким маршрутом: %s, и магазином: %s уже существует", ordersDto.getDriver(), ordersDto.getCustomer());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        Driver getDriver = driverService.findById(ordersDto.getDriver());
        Customer getCustomer = customerService.findById(ordersDto.getCustomer());

        if (getDriver == null || getCustomer == null){
            String errors = String.format("Не найден маршрут: %s или не найден заказчик: %s",
                    getDriver != null ? getDriver.getName() : ordersDto.getDriver(),
                    getCustomer != null ? getCustomer.getName() : ordersDto.getCustomer());

            logger.error(errors);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(errors).build());
        }

        ordersService.save(ordersDto);

        String result = String.format("Заказ успешно создан (маршрут: %s) (магазин: %s)", getDriver.getName(), getCustomer.getName());
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (ordersService.findById(id) == null){
            String error = String.format("Заказ с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            Orders orders = ordersService.findById(id);
            ordersService.delete(orders);

            String result = String.format("Заказ с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении заказа с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }

    @GetMapping("/delete-all")
    public ResponseEntity<?> deleteAll(){
        try {
            List<Orders> orders = ordersService.findAll();
            List<ProductList> productLists = productListService.findAll();

            productListService.deleteAll(productLists);
            ordersService.deleteAll(orders);

            String result = "Все заказы и списки продуктов, успешно удалены";
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении, сообщение: %s ", exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
