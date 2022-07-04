package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.customer.CustomerDto;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.repostitory.CustomerRepo;
import ru.chel.SRMPlayGround.service.CustomerService;
import ru.chel.SRMPlayGround.service.DriverService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;
    private DriverService driverService;

    public CustomerServiceImpl(CustomerRepo customerRepo, DriverService driverService) {
        this.customerRepo = customerRepo;
        this.driverService = driverService;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setPosition(customerDto.getPosition());
        customer.setDriver(driverService.findById(customerDto.getDriver()));

        return customerRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepo.findByName(name);
    }

    @Override
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }
}
