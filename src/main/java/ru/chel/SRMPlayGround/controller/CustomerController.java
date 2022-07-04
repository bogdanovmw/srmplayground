package ru.chel.SRMPlayGround.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.customer.CustomerDto;
import ru.chel.SRMPlayGround.dto.customer.CustomerPositionDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.service.CustomerService;
import ru.chel.SRMPlayGround.service.DriverService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    private DriverService driverService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService, DriverService driverService, DriverService driverService1) {
        this.customerService = customerService;
        this.driverService = driverService1;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()){
            String error = "Список магазинов пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(customers).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Customer customer = customerService.findById(id);

        if (customer == null){
            String error = String.format("Магазин с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(customer).build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (driverService.findById(customerDto.getDriver()) == null){
            String error = String.format("Маршрут с id: '%s' не найден", customerDto.getDriver());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (customerService.findByName(customerDto.getName()) != null){
            String error = String.format("Магазин с именем: '%s' уже существует", customerDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        customerService.save(customerDto);

        String result = String.format("Магазин добавлен: %s", customerDto);
        logger.error(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (driverService.findById(customerDto.getDriver()) == null){
            String error = String.format("Маршрут с id: '%s' не найден", customerDto.getDriver());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (customerService.findById(id) == null){
            String error = String.format("Магазин с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

//        if (customerService.findByName(customerDto.getName()) != null){
//            String error = String.format("Магазин с именем: '%s' уже существует", customerDto.getName());
//            logger.error(error);
//            return ResponseEntity.ok().body(error);
//        }

        Customer customer = customerService.findById(id);
        Driver driver = driverService.findById(customerDto.getDriver());
        customer.setName(customerDto.getName());
        customer.setPosition(customerDto.getPosition());
        customer.setDriver(driver);
        customerService.update(customer);

        String result = String.format("Данные магазина были обновлены: %s", customerDto);
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/position/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable Long id, @Valid @RequestBody CustomerPositionDto dto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (customerService.findById(id) == null){
            String error = String.format("Магазин с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        Customer customer = customerService.findById(id);
        customer.setPosition(dto.getPosition());
        customerService.update(customer);

        String result = String.format("Позиция магазина обновлена: %s", customer.getPosition());
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if (customerService.findById(id) == null){
            String error = String.format("Магазин с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            Customer customer = customerService.findById(id);
            customerService.delete(customer);

            String result = String.format("Магазин с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении магазина с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
