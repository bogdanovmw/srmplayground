package ru.chel.SRMPlayGround.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chel.SRMPlayGround.controller.utils.BindingValidate;
import ru.chel.SRMPlayGround.dto.DriverDto;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.service.DriverService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    private DriverService driverService;
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Driver> drivers = driverService.findAll();

        if (drivers.isEmpty()){
            String error = "Список маршрутов пуст";
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(drivers).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Driver driver = driverService.findById(id);

        if (driver == null){
            String error = String.format("Маршрут с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(driver).build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody DriverDto driverDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (driverService.findByName(driverDto.getName()) != null){
            String error = String.format("Маршрут с именем: '%s' уже существует", driverDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        driverService.save(driverDto);

        String result = String.format("Маршрут добавлен: %s", driverDto);
        logger.error(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DriverDto driverDto, BindingResult bindingResult){
        ResponseEntity<?> errorMap = BindingValidate.getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        if (driverService.findById(id) == null){
            String error = String.format("Маршрут с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        if (driverService.findByName(driverDto.getName()) != null){
            String error = String.format("Маршрут с именем: '%s' уже существует", driverDto.getName());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        Driver driver = driverService.findById(id);
        driver.setName(driverDto.getName());
        driverService.update(driver);

        String result = String.format("Данные маршрута были обновлены: %s", driverDto);
        logger.info(result);
        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (driverService.findById(id) == null){
            String error = String.format("Маршрут с id: '%s' не найден", id);
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data(error).build());
        }

        try{
            Driver driver = driverService.findById(id);
            driverService.delete(driver);

            String result = String.format("Маршрут с id: '%s' успешно удален", id);
            logger.info(result);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data(result).build());
        }catch (Exception exception){
            String error = String.format("Ошибка при удалении маршрута с id: %s, сообщение: %s ", id, exception.getMessage());
            logger.error(error);
            return ResponseEntity.ok().body(ResponseDto.builder().ok(false).data("Данная запись где-то используется.").build());
        }
    }
}
