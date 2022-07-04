package ru.chel.SRMPlayGround.service;


import ru.chel.SRMPlayGround.dto.customer.CustomerDto;
import ru.chel.SRMPlayGround.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(CustomerDto customerDto);
    Customer update(Customer customer);
    Customer findByName(String name);
    void delete(Customer customer);
}
