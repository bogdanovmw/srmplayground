package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.OrdersDto;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.repostitory.OrdersRepo;
import ru.chel.SRMPlayGround.service.CustomerService;
import ru.chel.SRMPlayGround.service.DriverService;
import ru.chel.SRMPlayGround.service.OrdersService;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepo ordersRepo;
    private DriverService driverService;
    private CustomerService customerService;

    public OrdersServiceImpl(OrdersRepo ordersRepo, DriverService driverService, CustomerService customerService) {
        this.ordersRepo = ordersRepo;
        this.driverService = driverService;
        this.customerService = customerService;
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepo.findAll();
    }

    @Override
    public List<Orders> findAllByDriverId(Long id) {
        return ordersRepo.findByDriversId(id);
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepo.findById(id).orElse(null);
    }

    @Override
    public Orders save(OrdersDto ordersDto) {
        Orders orders = new Orders();
        Driver getDriver = driverService.findById(ordersDto.getDriver());
        Customer getCustomer = customerService.findById(ordersDto.getCustomer());

        orders.setDrivers(getDriver);
        orders.setCustomers(getCustomer);

        return ordersRepo.save(orders);
    }

    @Override
    public void delete(Orders orders) {
        ordersRepo.delete(orders);
    }

    @Override
    public Orders findByCustomerIdAndDriverId(Long customer, Long driver) {
        return ordersRepo.findByCustomersIdAndDriversId(customer, driver);
    }

    @Override
    public List<Orders> findByDriversIdAndStatus(Long id, boolean status) {
        return ordersRepo.findByDriversIdAndStatus(id, status);
    }

    @Override
    public List<Orders> findAllAndStatus(boolean status) {
        return ordersRepo.findByStatus(status);
    }

    @Override
    public void deleteAll(List<Orders> orders) {
        ordersRepo.deleteAll(orders);
    }
}
