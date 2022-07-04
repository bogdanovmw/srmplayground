package ru.chel.SRMPlayGround.controller.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chel.SRMPlayGround.dto.response.ResponseDto;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.model.TypeProduct;
import ru.chel.SRMPlayGround.repostitory.CustomerRepo;
import ru.chel.SRMPlayGround.repostitory.DriverRepo;
import ru.chel.SRMPlayGround.repostitory.ProductRepo;
import ru.chel.SRMPlayGround.repostitory.TypeProductRepo;

import java.util.List;

@RestController
@RequestMapping("/api/all-to-up-case")
public class OtherController {
    private TypeProductRepo typeProductRepo;
    private ProductRepo productRepo;
    private DriverRepo driverRepo;
    private CustomerRepo customerRepo;

    public OtherController(TypeProductRepo typeProductRepo, ProductRepo productRepo, DriverRepo driverRepo, CustomerRepo customerRepo) {
        this.typeProductRepo = typeProductRepo;
        this.productRepo = productRepo;
        this.driverRepo = driverRepo;
        this.customerRepo = customerRepo;
    }

    private static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    @GetMapping
    public ResponseEntity<?> allToUpCase(){
//        List<TypeProduct> typeProducts = typeProductRepo.findAll();
//        for (TypeProduct tp : typeProducts){
//            tp.setName(firstUpperCase(tp.getName()));
//        }
//        typeProductRepo.saveAll(typeProducts);
//
//        List<Product> products = productRepo.findAll();
//        for (Product p : products){
//            p.setName(firstUpperCase(p.getName()));
//        }
//        productRepo.saveAll(products);
//
//        List<Driver> drivers = driverRepo.findAll();
//        for (Driver d : drivers){
//            d.setName(firstUpperCase(d.getName()));
//        }
//        driverRepo.saveAll(drivers);
//
//        List<Customer> customers = customerRepo.findAll();
//        for (Customer c : customers){
//            if (c.getName().contains("ИП ")){
//                c.setName("ИП " + firstUpperCase(c.getName().replace("ИП ", "")));
//            }else if(c.getName().contains("ооо ")){
//                c.setName("ООО " + firstUpperCase(c.getName().replace("ооо ", "")));
//            }else if(c.getName().contains("частное лицо ")) {
//                c.setName("Частное лицо " + firstUpperCase(c.getName().replace("частное лицо ", "")));
//            } else {
//                c.setName(firstUpperCase(c.getName()));
//            }
//        }
//        customerRepo.saveAll(customers);

        return ResponseEntity.ok().body(ResponseDto.builder().ok(true).data("Успешно").build());
    }

}
